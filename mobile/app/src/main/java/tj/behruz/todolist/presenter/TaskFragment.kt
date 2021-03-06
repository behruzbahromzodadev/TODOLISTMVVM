package tj.behruz.todolist.presenter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import tj.behruz.todolist.R
import tj.behruz.todolist.data.State
import tj.behruz.todolist.data.TaskAdapter
import tj.behruz.todolist.data.main.TaskViewModel
import tj.behruz.todolist.database.entities.Task
import tj.behruz.todolist.databinding.TaskFragmentBinding
import tj.behruz.todolist.utils.SwipeToDeleteCallback


class TaskFragment: Fragment() {

    private val viewModel by viewModels<TaskViewModel>()
    private val binding by lazy { TaskFragmentBinding.inflate(layoutInflater) }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = binding.root
    private val adapter by lazy { TaskAdapter(arrayListOf()) { handler(it) } }
    private val listOfTask = mutableListOf<Task>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initTaskList()
        search()

        lifecycleScope.launch {
            viewModel.getAll().collect { updateUI(it) }
        }

        binding.addTask.setOnClickListener {
            findNavController().navigate(R.id.action_taskFragment_to_addTaskFragment, bundleOf())
        }


    }

    private fun updateUI(state: State) {
        when (state) {
            is State.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            is State.Loaded -> {
                binding.progressBar.visibility = View.GONE
                listOfTask.addAll(state.tasks!!)
                adapter.dateChanged(state.tasks.toMutableList())
            }

            is State.Empty -> {
                binding.progressBar.visibility = View.GONE
                binding.notFound.visibility = View.VISIBLE
            }
            is State.LoadingFailed -> {
                Snackbar.make(binding.root, "Error", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun initTaskList() {
        binding.taskList.adapter = adapter
        val swipeHandler = object: SwipeToDeleteCallback(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = binding.taskList.adapter as TaskAdapter
                adapter.removeAt(viewHolder.adapterPosition)
                viewModel.delete(viewHolder.itemView.tag as Task)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(binding.taskList)

    }

    private fun handler(task: Task) {
        val bundle = bundleOf()
        bundle.putParcelable("task", task)
        findNavController().navigate(R.id.action_taskFragment_to_addTaskFragment, bundle)
    }

    private fun search(){
        binding.searchEditText.addTextChangedListener { type->
            if (type.toString().isNotEmpty()){
                adapter.dateChanged(listOfTask.filter { it.title.contains(type.toString()) }.toMutableList())
            }

        }
    }


}
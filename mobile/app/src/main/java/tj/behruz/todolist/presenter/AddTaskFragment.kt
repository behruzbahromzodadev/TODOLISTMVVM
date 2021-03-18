package tj.behruz.todolist.presenter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import tj.behruz.todolist.R
import tj.behruz.todolist.data.main.TaskViewModel
import tj.behruz.todolist.database.entities.Task
import tj.behruz.todolist.databinding.AddTaskFragmentBinding

class AddTaskFragment(): Fragment() {

    private val taskLevels = arrayListOf("High", "Normal", "Low")

    private val binding by lazy { AddTaskFragmentBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<TaskViewModel>()
    private var update = false
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = binding.root

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initDataBinding()
    }

    private fun initDataBinding() {
        val task = requireArguments().getParcelable<Task>("task")
        binding.level.setText(taskLevels[0])

        if (task != null) {
            binding.level.setText(task.priority)
            binding.title.setText(task.title)
            update = true
            binding.description.setText(task.description)
            binding.toolBarTitle.text = "Edit task"
        }

        val levelAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, taskLevels)
        binding.level.setAdapter(levelAdapter)
        binding.floatingActionButton.setOnClickListener {
            when {
                update -> {
                    viewModel.update(Task(task!!.id, binding.title.text.toString(), binding.description.text.toString(), false, binding.level.text.toString()))

                }
                else -> {
                    viewModel.insert(Task(0, binding.title.text.toString(), binding.description.text.toString(), false, binding.level.text.toString()))
                }
            }
            findNavController().navigate(R.id.action_global_taskFragment)
        }
        binding.backEvent.setOnClickListener {
                requireActivity().onBackPressed()
        }
    }

}



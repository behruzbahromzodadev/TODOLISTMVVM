package tj.behruz.todolist.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tj.behruz.todolist.database.entities.Task
import tj.behruz.todolist.databinding.TaskItemBinding
import tj.behruz.todolist.utils.setTaskBackground

class TaskAdapter(private var tasks: MutableList<Task>, private val handler: (Task) -> Unit): RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    override fun getItemCount() = tasks.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = TaskItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.setTask(tasks[position])
        holder.itemView.setOnClickListener {
            handler.invoke(tasks[position])
        }
    }


    fun removeAt(position: Int) {
        tasks.removeAt(position)
        notifyItemRemoved(position)
    }

    fun dateChanged(newList: MutableList<Task>) {
        tasks = newList
        notifyDataSetChanged()
    }

    inner class TaskViewHolder(private val binding: TaskItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun setTask(task: Task) {
            binding.toolBarTitle.text = task.title
            binding.description.text = task.description
            binding.level.text = task.priority
            binding.levelBackground.setTaskBackground(task.priority)
            binding.root.tag = task
            binding.executePendingBindings()
        }

    }

}
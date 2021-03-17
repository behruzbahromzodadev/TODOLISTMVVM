package tj.behruz.todolist.data.main

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tj.behruz.todolist.data.repositories.TaskRepository
import tj.behruz.todolist.database.entities.Task
import tj.behruz.todolist.di.BaseViewModel
import javax.inject.Inject

class TaskViewModel : BaseViewModel() {

    @Inject
    lateinit var taskRepository: TaskRepository

    fun insert(task: Task) {

        viewModelScope.launch {
            taskRepository.insert(task)
        }


    }


}
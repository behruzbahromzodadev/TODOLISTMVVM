package tj.behruz.todolist.data.main

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import tj.behruz.todolist.data.State
import tj.behruz.todolist.data.repositories.TaskRepository
import tj.behruz.todolist.database.entities.Task
import tj.behruz.todolist.di.BaseViewModel
import javax.inject.Inject

class TaskViewModel: BaseViewModel() {

    @Inject
    lateinit var taskRepository: TaskRepository

    fun insert(task: Task) {
        viewModelScope.launch {
            taskRepository.insert(task)
        }
    }


    fun getAll(): Flow<State> {
        val result = MutableStateFlow<State>(State.Loading)
        viewModelScope.launch {
            taskRepository.getAll()
                .collect {
                    result.value = it
                }
        }
        return result
    }

    fun delete(task: Task){
        viewModelScope.launch {
            taskRepository.delete(task)
        }
    }

    fun update(task: Task){
        viewModelScope.launch {
            taskRepository.update(task)
        }
    }
}
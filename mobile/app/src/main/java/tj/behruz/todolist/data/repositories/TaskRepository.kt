package tj.behruz.todolist.data.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import tj.behruz.todolist.data.State
import tj.behruz.todolist.database.dao.TaskDao
import tj.behruz.todolist.database.entities.Task
import tj.behruz.todolist.di.BaseRepository
import javax.inject.Inject

class TaskRepository: BaseRepository() {

    @Inject
    lateinit var taskDao: TaskDao

    suspend fun insert(task: Task) {
        taskDao.insert(task)
    }

    suspend fun getAll(): Flow<State> {

        return flow {
            taskDao.getAll().onStart {
                emit(State.Loading)
            }.collect {
                if (it.isNotEmpty()) {
                    emit(State.Loaded(it))
                } else {
                    emit(State.Empty)
                }

            }
        }.catch {
            emit(State.LoadingFailed(1))
        }.flowOn(Dispatchers.IO)

    }

    suspend fun update(task: Task) {
        taskDao.update(task)
    }


    suspend fun delete(task: Task) {
        taskDao.delete(task)
    }


}





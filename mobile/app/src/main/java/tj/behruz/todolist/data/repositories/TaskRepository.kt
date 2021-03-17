package tj.behruz.todolist.data.repositories

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




}
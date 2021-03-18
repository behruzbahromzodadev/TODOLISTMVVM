package tj.behruz.todolist.database

import androidx.room.Database
import androidx.room.RoomDatabase
import tj.behruz.todolist.database.dao.TaskDao
import tj.behruz.todolist.database.entities.Task

@Database(entities = [Task::class],exportSchema = false,version = 1)
abstract class TaskDataBase:RoomDatabase() {

    abstract fun taskDao():TaskDao

}
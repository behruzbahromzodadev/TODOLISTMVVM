package tj.behruz.todolist.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import tj.behruz.todolist.database.TaskDataBase
import tj.behruz.todolist.database.dao.TaskDao
import javax.inject.Singleton

@Module
class DataBaseModule(application: Application) {

    private val taskDataBase :TaskDataBase
    init {
        taskDataBase = Room.databaseBuilder(
            application.applicationContext,
            TaskDataBase::class.java,
            "task"
        ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
    }
    @Singleton
    @Provides
    internal fun providesRoomDatabase(): TaskDataBase {
        return taskDataBase
    }

    @Singleton
    @Provides
    internal fun providesTaskDao(taskDataBase: TaskDataBase): TaskDao {
        return taskDataBase.taskDao()
    }





}
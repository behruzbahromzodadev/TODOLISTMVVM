package tj.behruz.todolist.di

import tj.behruz.todolist.TodoApplication
import tj.behruz.todolist.data.repositories.TaskRepository

abstract class BaseRepository {
    private val injector: RepositoryInjector = DaggerRepositoryInjector.builder().dataBaseModule(DataBaseModule(TodoApplication.instance!!)).build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {

        when(this){

            is TaskRepository -> injector.inject(this)
        }

    }

}
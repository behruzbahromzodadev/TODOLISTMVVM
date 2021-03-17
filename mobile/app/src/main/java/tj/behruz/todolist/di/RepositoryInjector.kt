package tj.behruz.todolist.di

import dagger.Component
import tj.behruz.todolist.data.repositories.TaskRepository
import javax.inject.Singleton

@Singleton
@Component(modules = [DataBaseModule::class])
interface RepositoryInjector {


    fun inject(taskRepository: TaskRepository)

    @Component.Builder
    interface Builder {
        fun build(): RepositoryInjector

        fun dataBaseModule(dataBaseModule: DataBaseModule): Builder
    }


}
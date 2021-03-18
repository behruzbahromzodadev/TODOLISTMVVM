package tj.behruz.todolist.di

import dagger.Component
import tj.behruz.todolist.data.main.TaskViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class])
interface ViewModelInjector {
    fun inject(taskViewModel: TaskViewModel)
    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun repositoryModule(repositoryModule: RepositoryModule): Builder
    }
}
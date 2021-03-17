package tj.behruz.todolist.di

import dagger.Module
import dagger.Provides
import tj.behruz.todolist.data.repositories.TaskRepository
import javax.inject.Singleton


@Module
object RepositoryModule {
    @Singleton
    @Provides
    internal fun provideTaskRepository(): TaskRepository {
        return TaskRepository()
    }
}
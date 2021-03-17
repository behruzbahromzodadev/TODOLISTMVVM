package tj.behruz.todolist.di

import androidx.lifecycle.ViewModel
import tj.behruz.todolist.data.main.TaskViewModel


abstract class BaseViewModel:ViewModel() {

    private val injector: ViewModelInjector = DaggerViewModelInjector.builder().repositoryModule(RepositoryModule).build()

    init {
        inject()
    }


    private fun inject(){
        when(this){
            is TaskViewModel->injector.inject(this)


        }
    }
}
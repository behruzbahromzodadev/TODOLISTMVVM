package tj.behruz.todolist.data

import tj.behruz.todolist.database.entities.Task

sealed class State{

    object Empty : State()
    object Loading : State()
    class LoadingFailed(val errorCode : Int) : State()
    class Loaded(val tasks: List<Task>?) : State()

}

package tj.behruz.todolist

import android.app.Application
import android.content.Context


class TodoApplication:Application() {

    init {
        instance=this
    }


    companion object {
        var instance: TodoApplication? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }

    }
}
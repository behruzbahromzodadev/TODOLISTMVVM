package tj.behruz.todolist.utils

import android.graphics.Color
import android.view.View

fun View.setTaskBackground(level:String){
    when(level){
         "High"->this.setBackgroundColor(Color.RED)

        "Normal"->this.setBackgroundColor(Color.YELLOW)

        "Low"->this.setBackgroundColor(Color.GREEN)
    }
}
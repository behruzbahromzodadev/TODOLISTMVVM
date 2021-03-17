package tj.behruz.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import tj.behruz.todolist.data.main.TaskViewModel
import tj.behruz.todolist.database.entities.Task

class HomeActivity: AppCompatActivity() {

    private val viewModel by viewModels<TaskViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        viewModel.insert(Task(1,"","",false,""))
    }


}
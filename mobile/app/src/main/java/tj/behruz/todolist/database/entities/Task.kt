package tj.behruz.todolist.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id:Long,
    val title:String,
    val description:String,
    val status:Boolean,
    val priority:String

)

package tj.behruz.todolist.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import tj.behruz.todolist.database.entities.Task

@Dao
interface TaskDao {

    @Insert
    suspend fun insert(task: Task)

    @Query("Select * from task")
    fun getAll(): Flow<List<Task>>



}
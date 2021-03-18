package tj.behruz.todolist.database.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import tj.behruz.todolist.database.entities.Task

@Dao
interface TaskDao {

    @Insert
    suspend fun insert(task: Task)

    @Query("Select * from task order by id desc")
    fun getAll(): Flow<List<Task>>

    @Delete
    suspend fun delete(task: Task)

    @Update
    suspend fun update(task: Task)

}
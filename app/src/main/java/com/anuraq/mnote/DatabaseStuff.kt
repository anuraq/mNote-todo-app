package com.anuraq.mnote

import android.content.Context
import androidx.room.*

@Entity
data class Task(
    @PrimaryKey val tid: Int,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "description") val desc: String?
)

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun getAll(): List<Task>

    @Insert
    fun insertAll(vararg tasks: Task)

    @Delete
    fun delete(task: Task)
}

@Database(entities = arrayOf(Task::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}

class DatabaseR(val cont: Context) : Runnable {

    private lateinit var dataSS: List<Task>

    override fun run(){
        val db = Room.databaseBuilder(
            cont,
            AppDatabase::class.java, "tasks-database"
        ).build()
        db.taskDao().insertAll(Task(1, "Anurag", "123"), Task(2, "Aditya", "456"))
        dataSS = db.taskDao().getAll()
    }

    fun getValue(): List<Task> {
        return dataSS
    }
}
package com.anuraq.mnote.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun getAll(): LiveData<List<Task>>

    @Insert
    fun insertAll(vararg tasks: Task)

    @Insert
    fun insert(task: Task)

    @Delete
    fun delete(task: Task)
}
package com.anuraq.mnote.model

import android.content.Context
import androidx.room.*
import java.sql.Time
import java.util.*

class DatabaseR(val cont: Context) : Runnable {

    private lateinit var dataSS: List<Task>
    private lateinit var db: AppDatabase

    override fun run(){
        db = Room.databaseBuilder(
            cont,
            AppDatabase::class.java, "tasks-database"
        ).allowMainThreadQueries().build()
    }

    fun insertValue(title: String, desc: String) {
        val r: Random = Random()
        db.taskDao().insertAll(Task(r.nextInt(), title, desc))
    }

    fun getValue(): List<Task> {
        //dataSS = db.taskDao().getAll()
        return dataSS
    }
}
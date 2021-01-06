package com.anuraq.mnote.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.anuraq.mnote.model.AppDatabase
import com.anuraq.mnote.model.Task
import com.anuraq.mnote.model.TaskDao

class TaskRepository {
    private var taskDao: TaskDao
    private lateinit var allSubjects: LiveData<List<Task>>

    constructor(application: Application){
        val database: AppDatabase = AppDatabase.getInstance(application)
        taskDao = database.taskDao()
    }

    companion object{
        private var instance: TaskRepository? = null

        fun getInstance(application: Application): TaskRepository{
            if (instance == null) {
                instance = TaskRepository(application)
            }
            return instance as TaskRepository
        }
    }

    fun insert(t: Task) { taskDao.insert(t) }

    fun getAll(): List<Task> { return taskDao.getAll() }

    fun delete(t: Task) { taskDao.delete(t) }

}
package com.anuraq.mnote.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.anuraq.mnote.model.Task
import com.anuraq.mnote.repositories.TaskRepository

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    private var taskRepository: TaskRepository = TaskRepository.getInstance(application)
    private var allTasks : LiveData<List<Task>>
    init{
        allTasks = taskRepository.getAll()
    }

    fun insert(t: Task) { taskRepository.insert(t) }

    fun getAll(): LiveData<List<Task>> { return taskRepository.getAll() }

    fun delete(t: Task) { taskRepository.delete(t) }
}
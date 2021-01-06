package com.anuraq.mnote.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.anuraq.mnote.model.Task
import com.anuraq.mnote.repositories.TaskRepository

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    private var taskRepository: TaskRepository = TaskRepository.getInstance(application)
    var allTasks : MutableLiveData<List<Task>> = MutableLiveData<List<Task>>()
    init{
        allTasks.value = taskRepository.getAll()
    }

    fun insert(t: Task) { taskRepository.insert(t) }

    fun getAll(): List<Task> { return taskRepository.getAll() }

    fun delete(t: Task) { taskRepository.delete(t) }
}
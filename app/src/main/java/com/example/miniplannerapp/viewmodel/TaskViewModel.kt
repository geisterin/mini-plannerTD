package com.example.miniplannerapp.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import com.example.miniplannerapp.data.Task
import com.example.miniplannerapp.data.TaskStorage
import java.util.UUID

class TaskViewModel(app: Application) : AndroidViewModel(app) {

    private val storage = TaskStorage(app.applicationContext)
    val tasks = mutableStateListOf<Task>()

    init {
        tasks.addAll(storage.load())
    }

    fun addTask(text: String, date: String?) {
        val t = text.trim()
        if (t.isEmpty()) return

        tasks.add(
            0,
            Task(
                id = UUID.randomUUID().toString(),
                text = t,
                date = date?.trim()?.takeIf { it.isNotEmpty() }
            )
        )
        storage.save(tasks)
    }

    fun toggleDone(id: String, done: Boolean) {
        val idx = tasks.indexOfFirst { it.id == id }
        if (idx >= 0) {
            tasks[idx] = tasks[idx].copy(done = done)
            storage.save(tasks)
        }
    }

    fun deleteTask(id: String) {
        tasks.removeAll { it.id == id }
        storage.save(tasks)
    }
}
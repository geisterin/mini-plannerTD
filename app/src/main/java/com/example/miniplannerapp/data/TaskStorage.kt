package com.example.miniplannerapp.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TaskStorage(context: Context) {

    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    private val gson = Gson()

    fun save(tasks: List<Task>) {
        prefs.edit()
            .putString(KEY_TASKS, gson.toJson(tasks))
            .apply()
    }

    fun load(): List<Task> {
        val json = prefs.getString(KEY_TASKS, null) ?: return emptyList()
        val type = object : TypeToken<List<Task>>() {}.type
        return gson.fromJson<List<Task>>(json, type) ?: emptyList()
    }

    private companion object {
        private const val PREFS_NAME = "mini_planner_prefs"
        private const val KEY_TASKS = "tasks_json"
    }
}
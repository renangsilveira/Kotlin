package com.task.tracker.repository

import com.task.tracker.model.Task
import java.io.File
import org.json.JSONArray
import org.json.JSONObject

class TaskRepository(private val fileName: String = "tasks.json") {

    private val file = File(fileName)

    init {
        if (!file.exists()) {
            file.writeText("[]")
        }
    }

    fun findAll(): MutableList<Task> {
        println("Reading tasks from file")
        val content = file.readText()
        val jsonArray = JSONArray(content)
        return mutableListOf<Task>().apply {
            for (i in 0 until jsonArray.length()) {
                val json = jsonArray.getJSONObject(i)
                add(
                    Task(
                        id = json.getInt("id"),
                        description = json.getString("description"),
                        status = json.getString("status"),
                        createdAt = json.getString("createdAt"),
                        updatedAt = json.getString("updatedAt")
                    )
                )
            }
        }
    }

    fun saveAll(tasks: List<Task>) {
        println("Saving tasks to file")
        val jsonArray = JSONArray()
        tasks.forEach {
            val json = JSONObject()
            json.put("id", it.id)
            json.put("description", it.description)
            json.put("status", it.status)
            json.put("createdAt", it.createdAt)
            json.put("updatedAt", it.updatedAt)
            jsonArray.put(json)
        }
        file.writeText(jsonArray.toString(2))
    }
}
package com.task.tracker.model

data class Task(
    val id: Int,
    var description: String,
    var status: String, // Possibilities = todo, in-progress, done
    val createdAt: String,
    var updatedAt: String
)
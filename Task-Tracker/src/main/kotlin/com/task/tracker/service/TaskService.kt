package com.task.tracker.service

import com.task.tracker.dto.TaskDto
import com.task.tracker.model.Task
import com.task.tracker.repository.TaskRepository
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TaskService(private val repository: TaskRepository) {

    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    fun add(taskDto: TaskDto): Task {
        val tasks = repository.findAll()
        val newId = (tasks.maxOfOrNull { it.id } ?: 0) + 1
        val now = LocalDateTime.now().format(formatter)
        val newTask = Task(newId, taskDto.description, "todo", now, now)
        tasks.add(newTask)
        repository.saveAll(tasks)
        return newTask
    }

    fun update(id: Int, description: String): Boolean {
        val tasks = repository.findAll()
        val task = tasks.find { it.id == id } ?: return false
        task.description = description
        task.updatedAt = LocalDateTime.now().format(formatter)
        repository.saveAll(tasks)
        return true
    }

    fun delete(id: Int): Boolean {
        val tasks = repository.findAll()
        val removed = tasks.removeIf { it.id == id }
        if (removed) repository.saveAll(tasks)
        return removed
    }

    fun changeStatus(id: Int, status: String): Boolean {
        val tasks = repository.findAll()
        val task = tasks.find { it.id == id } ?: return false
        task.status = status
        task.updatedAt = LocalDateTime.now().format(formatter)
        repository.saveAll(tasks)
        return true
    }

    fun list(status: String? = null): List<Task> {
        val tasks = repository.findAll()
        return status?.let { tasks.filter { it.status == status } } ?: tasks
    }
}
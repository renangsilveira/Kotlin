package com.task.tracker.controller

import com.task.tracker.dto.TaskDto
import com.task.tracker.service.TaskService


class TaskController(private val service: TaskService) {

    fun handle(args: Array<String>) {
        if (args.isEmpty()) {
            println("Comando inválido. Use: add | update | delete | mark-in-progress | mark-done | list")
            return
        }

        when (args[0]) {
            "add" -> {
                val description = args.getOrNull(1) ?: return println("Descrição obrigatória.")
                val task = service.add(TaskDto(description))
                println("Task added successfully (ID: ${task.id})")
            }

            "update" -> {
                val id = args.getOrNull(1)?.toIntOrNull() ?: return println("ID inválido.")
                val description = args.getOrNull(2) ?: return println("Descrição obrigatória.")
                if (service.update(id, description))
                    println("Task updated successfully")
                else
                    println("Task with ID $id not found")
            }

            "delete" -> {
                val id = args.getOrNull(1)?.toIntOrNull() ?: return println("ID inválido.")
                if (service.delete(id))
                    println("Task deleted successfully")
                else
                    println("Task with ID $id not found")
            }

            "mark-in-progress" -> {
                val id = args.getOrNull(1)?.toIntOrNull() ?: return println("ID inválido.")
                if (service.changeStatus(id, "in-progress"))
                    println("Task marked as in-progress")
                else
                    println("Task with ID $id not found")
            }

            "mark-done" -> {
                val id = args.getOrNull(1)?.toIntOrNull() ?: return println("ID inválido.")
                if (service.changeStatus(id, "done"))
                    println("Task marked as done")
                else
                    println("Task with ID $id not found")
            }

            "list" -> {
                val status = args.getOrNull(1)
                val tasks = service.list(status)
                if (tasks.isEmpty()) {
                    println("No tasks found")
                } else {
                    tasks.forEach {
                        println("ID: ${it.id}")
                        println("Description: ${it.description}")
                        println("Status: ${it.status}")
                        println("Created At: ${it.createdAt}")
                        println("Updated At: ${it.updatedAt}")
                        println("----------")
                    }
                }
            }

            else -> println("Comando não reconhecido.")
        }
    }
}
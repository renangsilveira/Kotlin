package com.task.tracker

import com.task.tracker.controller.TaskController
import com.task.tracker.repository.TaskRepository
import com.task.tracker.service.TaskService
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class TaskTrackerApplication

fun main(args: Array<String>) {
	val repository = TaskRepository()
	val service = TaskService(repository)
	val controller = TaskController(service)
	controller.handle(args)
}
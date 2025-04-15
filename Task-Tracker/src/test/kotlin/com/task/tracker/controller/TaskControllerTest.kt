package com.task.tracker.controller

import com.task.tracker.dto.TaskDto
import com.task.tracker.model.Task
import com.task.tracker.service.TaskService
import org.junit.jupiter.api.*
import org.mockito.kotlin.*
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.time.LocalDateTime
import kotlin.test.assertTrue

class TaskControllerTest {

    private lateinit var service: TaskService
    private lateinit var controller: TaskController

    private val stdOut = ByteArrayOutputStream()
    private val originalOut = System.out

    @BeforeEach
    fun setup() {
        service = mock()
        controller = TaskController(service)
        System.setOut(PrintStream(stdOut)) // redireciona o output
    }

    @AfterEach
    fun teardown() {
        System.setOut(originalOut) // restaura o stdout
        stdOut.reset()
    }

    @Test
    fun `should add task`() {
        val dto = TaskDto("New task")
        val task = Task(1, dto.description, "pending", LocalDateTime.now().toString(), LocalDateTime.now().toString())

        whenever(service.add(dto)).thenReturn(task)

        controller.handle(arrayOf("add", "New task"))

        assertTrue(stdOut.toString().contains("Task added successfully (ID: 1)"))
    }

    @Test
    fun `should show error on add without description`() {
        controller.handle(arrayOf("add"))

        assertTrue(stdOut.toString().contains("Must add description."))
    }

    @Test
    fun `should update task successfully`() {
        whenever(service.update(1, "Updated")).thenReturn(true)

        controller.handle(arrayOf("update", "1", "Updated"))

        assertTrue(stdOut.toString().contains("Task updated successfully"))
    }

    @Test
    fun `should show error if update fails`() {
        whenever(service.update(1, "Updated")).thenReturn(false)

        controller.handle(arrayOf("update", "1", "Updated"))

        assertTrue(stdOut.toString().contains("Task with ID 1 not found"))
    }

    @Test
    fun `should delete task successfully`() {
        whenever(service.delete(1)).thenReturn(true)

        controller.handle(arrayOf("delete", "1"))

        assertTrue(stdOut.toString().contains("Task deleted successfully"))
    }

    @Test
    fun `should show error if delete fails`() {
        whenever(service.delete(1)).thenReturn(false)

        controller.handle(arrayOf("delete", "1"))

        assertTrue(stdOut.toString().contains("Task with ID 1 not found"))
    }

    @Test
    fun `should mark task as in-progress`() {
        whenever(service.changeStatus(1, "in-progress")).thenReturn(true)

        controller.handle(arrayOf("mark-in-progress", "1"))

        assertTrue(stdOut.toString().contains("Task marked as in-progress"))
    }

    @Test
    fun `should mark task as done`() {
        whenever(service.changeStatus(1, "done")).thenReturn(true)

        controller.handle(arrayOf("mark-done", "1"))

        assertTrue(stdOut.toString().contains("Task marked as done"))
    }

    @Test
    fun `should list tasks`() {
        val tasks = listOf(
            Task(1, "Testing", "pending", LocalDateTime.now().toString(), LocalDateTime.now().toString())
        )

        whenever(service.list(null)).thenReturn(tasks)

        controller.handle(arrayOf("list"))

        val output = stdOut.toString()
        assertTrue(output.contains("ID: 1"))
        assertTrue(output.contains("Description: Testing"))
        assertTrue(output.contains("Status: pending"))
    }

    @Test
    fun `should show no tasks message`() {
        whenever(service.list(null)).thenReturn(emptyList())

        controller.handle(arrayOf("list"))

        assertTrue(stdOut.toString().contains("No tasks found"))
    }

    @Test
    fun `should show help message for unknown command`() {
        controller.handle(arrayOf("foobar"))

        assertTrue(stdOut.toString().contains("Command not recognized"))
    }

    @Test
    fun `should show help message for empty args`() {
        controller.handle(emptyArray())

        assertTrue(stdOut.toString().contains("Invalid Command"))
    }
}
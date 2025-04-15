package com.task.tracker.service

import com.task.tracker.dto.TaskDto
import com.task.tracker.model.Task
import com.task.tracker.repository.TaskRepository
import org.junit.jupiter.api.*
import org.mockito.kotlin.*
import java.time.LocalDateTime
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class TaskServiceTest {

    private lateinit var repository: TaskRepository
    private lateinit var service: TaskService

    @BeforeEach
    fun setup() {
        repository = mock()
        service = TaskService(repository)
    }

    @Test
    fun `should add new task with correct ID and timestamps`() {
        whenever(repository.findAll()).thenReturn(mutableListOf())

        val dto = TaskDto("New Task")
        val task = service.add(dto)

        assertEquals(1, task.id)
        assertEquals("New Task", task.description)
        assertEquals("todo", task.status)

        verify(repository).saveAll(check {
            assertEquals(1, it.size)
            assertEquals(task, it.first())
        })
    }

    @Test
    fun `should increment ID based on existing tasks`() {
        val existing = listOf(
            Task(1, "Old", "done", "2023-01-01T10:00:00", "2023-01-01T10:00:00")
        )
        whenever(repository.findAll()).thenReturn(existing.toMutableList())

        val dto = TaskDto("New task")
        val task = service.add(dto)

        assertEquals(2, task.id)
    }

    @Test
    fun `should update existing task`() {
        val task = Task(1, "Old Description", "todo", "2023-01-01T10:00:00", "2023-01-01T10:00:00")
        whenever(repository.findAll()).thenReturn(mutableListOf(task))

        val result = service.update(1, "New Description")

        assertTrue(result)
        assertEquals("New Description", task.description)
        verify(repository).saveAll(listOf(task))
    }

    @Test
    fun `should return false if task to update is not found`() {
        whenever(repository.findAll()).thenReturn(mutableListOf())

        val result = service.update(99, "Anything")

        assertFalse(result)
        verify(repository, never()).saveAll(any())
    }

    @Test
    fun `should delete task by ID`() {
        val task = Task(1, "Desc", "todo", "x", "y")
        val tasks = mutableListOf(task)
        whenever(repository.findAll()).thenReturn(tasks)

        val result = service.delete(1)

        assertTrue(result)
        verify(repository).saveAll(emptyList())
    }

    @Test
    fun `should return false if no task deleted`() {
        val tasks = mutableListOf(Task(2, "Other", "done", "x", "y"))
        whenever(repository.findAll()).thenReturn(tasks)

        val result = service.delete(99)

        assertFalse(result)
        verify(repository, never()).saveAll(any())
    }

    @Test
    fun `should change task status`() {
        val task = Task(1, "Test", "todo", "x", "y")
        whenever(repository.findAll()).thenReturn(mutableListOf(task))

        val result = service.changeStatus(1, "done")

        assertTrue(result)
        assertEquals("done", task.status)
        verify(repository).saveAll(listOf(task))
    }

    @Test
    fun `should return false if task to change status not found`() {
        whenever(repository.findAll()).thenReturn(mutableListOf())

        val result = service.changeStatus(1, "done")

        assertFalse(result)
        verify(repository, never()).saveAll(any())
    }

    @Test
    fun `should list all tasks if no status provided`() {
        val tasks = listOf(Task(1, "A", "todo", "x", "y"))
        whenever(repository.findAll()).thenReturn(tasks.toMutableList())

        val result = service.list()

        assertEquals(1, result.size)
        assertEquals("A", result.first().description)
    }

    @Test
    fun `should filter tasks by status`() {
        val tasks = listOf(
            Task(1, "A", "todo", "x", "y"),
            Task(2, "B", "done", "x", "y")
        )
        whenever(repository.findAll()).thenReturn(tasks.toMutableList())

        val result = service.list("done")

        assertEquals(1, result.size)
        assertEquals("B", result.first().description)
    }
}
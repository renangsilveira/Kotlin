package com.task.tracker.repository

import com.task.tracker.model.Task
import org.junit.jupiter.api.*
import java.io.File
import java.time.LocalDateTime
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TaskRepositoryTest {

    private lateinit var repo: TaskRepository
    private val testFileName = "test-tasks.json"

    @BeforeEach
    fun setup() {
        // Apaga o arquivo se existir
        File(testFileName).delete()
        repo = TaskRepository(testFileName)
    }

    @AfterEach
    fun teardown() {
        File(testFileName).delete()
    }

    @Test
    fun `should initialize with empty file if not exists`() {
        val file = File(testFileName)
        assertTrue(file.exists())
        assertEquals("[]", file.readText().trim())
    }

    @Test
    fun `should save tasks to file`() {
        val now = LocalDateTime.now().toString()
        val tasks = listOf(
            Task(1, "Test save", "todo", now, now),
            Task(2, "Another task", "done", now, now)
        )

        repo.saveAll(tasks)

        val content = File(testFileName).readText()
        assertTrue(content.contains("Test save"))
        assertTrue(content.contains("Another task"))
    }

    @Test
    fun `should read tasks from file`() {
        val now = LocalDateTime.now().toString()
        val initialTasks = listOf(
            Task(1, "Saved", "todo", now, now),
            Task(2, "Other", "done", now, now)
        )

        repo.saveAll(initialTasks)

        val result = repo.findAll()

        assertEquals(2, result.size)
        assertEquals("Saved", result[0].description)
        assertEquals("Other", result[1].description)
    }

    @Test
    fun `should return empty list if file is empty array`() {
        File(testFileName).writeText("[]")

        val result = repo.findAll()

        assertTrue(result.isEmpty())
    }
}
package com.task.tracker.model

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class TaskTest {

    @Test
    fun `should create task with correct values`() {
        val task = Task(
            id = 1,
            description = "Write tests",
            status = "todo",
            createdAt = "2025-04-14T10:00:00",
            updatedAt = "2025-04-14T10:00:00"
        )

        assertEquals(1, task.id)
        assertEquals("Write tests", task.description)
        assertEquals("todo", task.status)
        assertEquals("2025-04-14T10:00:00", task.createdAt)
        assertEquals("2025-04-14T10:00:00", task.updatedAt)
    }

    @Test
    fun `should allow updating mutable fields`() {
        val task = Task(1, "Desc", "todo", "2025-04-14", "2025-04-14")

        task.description = "New description"
        task.status = "done"
        task.updatedAt = "2025-04-15"

        assertEquals("New description", task.description)
        assertEquals("done", task.status)
        assertEquals("2025-04-15", task.updatedAt)
    }

    @Test
    fun `should copy task and override fields`() {
        val original = Task(1, "Desc", "todo", "2025-04-14", "2025-04-14")
        val copy = original.copy(status = "in-progress")

        assertEquals("in-progress", copy.status)
        assertEquals("todo", original.status)
    }

    @Test
    fun `should compare two equal tasks`() {
        val t1 = Task(1, "X", "todo", "2025-04-14", "2025-04-14")
        val t2 = Task(1, "X", "todo", "2025-04-14", "2025-04-14")

        assertEquals(t1, t2)
    }

    @Test
    fun `should detect different tasks`() {
        val t1 = Task(1, "A", "todo", "2025-04-14", "2025-04-14")
        val t2 = Task(2, "B", "done", "2025-04-14", "2025-04-14")

        assertNotEquals(t1, t2)
    }
}
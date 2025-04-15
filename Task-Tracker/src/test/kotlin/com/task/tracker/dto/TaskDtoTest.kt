package com.task.tracker.dto

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class TaskDtoTest {

    @Test
    fun `should create TaskDto with correct description`() {
        val dto = TaskDto("Study Kotlin")
        assertEquals("Study Kotlin", dto.description)
    }

    @Test
    fun `should compare two equal TaskDto objects`() {
        val dto1 = TaskDto("To make coffee")
        val dto2 = TaskDto("To make coffee")
        assertEquals(dto1, dto2)
    }

    @Test
    fun `should detect different TaskDto objects`() {
        val dto1 = TaskDto("Read book")
        val dto2 = TaskDto("Write code")
        assertNotEquals(dto1, dto2)
    }

    @Test
    fun `should support copy with modification`() {
        val original = TaskDto("Original task")
        val copy = original.copy(description = "Modified Task")

        assertEquals("Original task", original.description)
        assertEquals("Modified Task", copy.description)
    }
}
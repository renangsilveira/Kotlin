package com.Order.Processor.service

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class NotificationServiceTest {

    private lateinit var service: NotificationService

    @BeforeEach
    fun setUp() {
        service = NotificationService()
    }

    @Test
    fun `should send notification with correct email`() = runTest {
        val outContent = ByteArrayOutputStream()
        System.setOut(PrintStream(outContent))

        val email = "test@example.com"
        service.sendEmail(email)

        val output = outContent.toString()
        assertTrue(output.contains("Notification sent to $email"))
    }
}
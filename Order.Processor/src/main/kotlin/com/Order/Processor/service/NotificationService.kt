package com.Order.Processor.service

import kotlinx.coroutines.delay
import org.springframework.stereotype.Service

@Service
class NotificationService {

    suspend fun sendEmail(email: String) {
        println("[asynchronous] Starting to send notification to $email...")
        delay(20000) // Simulate sending e-mail
        println("[asynchronous] Notification sent to $email")
    }
}
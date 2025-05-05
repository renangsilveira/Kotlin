package com.Order.Processor.service

import com.Order.Processor.dto.OrderDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.springframework.stereotype.Service

@Service
class OrderService(
    private val notificationService: NotificationService,
    private val coroutineScope: CoroutineScope
) {
    fun processOrder(dto: OrderDto) {
        println("[synchronous] Saving order for product: ${dto.product}")

        coroutineScope.launch {
            notificationService.sendEmail(dto.customerEmail)
        }

        println("[synchronous] Order processing complete")
    }
}
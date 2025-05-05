package com.Order.Processor.controller

import com.Order.Processor.dto.OrderDto
import com.Order.Processor.service.OrderService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/orders")
class OrderController(
    private val orderService: OrderService
) {
    @PostMapping
    fun createOrder(@RequestBody dto: OrderDto): ResponseEntity<String> {
        orderService.processOrder(dto)
        return ResponseEntity.ok("Order received successfully!")
    }
}
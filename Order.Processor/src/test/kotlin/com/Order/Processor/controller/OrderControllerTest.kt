package com.Order.Processor.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.Order.Processor.dto.OrderDto
import com.Order.Processor.service.OrderService
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doNothing
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@WebMvcTest(OrderController::class)
class OrderControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var orderService: OrderService

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Test
    fun `should return 200 OK when order is created`() {
        // Arrange
        val dto = OrderDto("cliente@meli.com", "Mouse Gamer")
        doNothing().whenever(orderService).processOrder(dto)

        // Act & Assert
        mockMvc.post("/orders") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(dto)
        }.andExpect {
            status { isOk() }
            content { string("Order received successfully!") }
        }

        // Verify interaction
        verify(orderService).processOrder(dto)
    }
}
package com.Order.Processor.service

import com.Order.Processor.dto.OrderDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@OptIn(ExperimentalCoroutinesApi::class)
class OrderServiceTest {

    private lateinit var notificationService: NotificationService
    private lateinit var testScope: TestScope
    private lateinit var orderService: OrderService

    @BeforeEach
    fun setUp() {
        notificationService = mock()
        testScope = TestScope()
        orderService = OrderService(notificationService, testScope)
    }

    @Test
    fun `should call notificationService asynchronously`() = testScope.runTest {
        val dto = OrderDto("test@meli.com", "Keyboard")

        orderService.processOrder(dto)
        advanceUntilIdle()

        verify(notificationService).sendEmail("test@meli.com")
    }
}
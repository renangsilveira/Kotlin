# Order Processor

This is a Kotlin + Spring Boot project that demonstrates how to handle both synchronous and asynchronous execution flows using coroutines in a clean and production-safe way.

## 📋 Project Description

The **Order Processor API** allows clients to register a new order via HTTP. Upon receiving an order:

- The order is **saved synchronously** (simulated with a print statement).
- A **notification is sent asynchronously** to the customer using Kotlin coroutines and a properly injected `CoroutineScope`.

The project illustrates how to:
- Structure a simple REST API with Spring Boot and Kotlin.
- Use `CoroutineScope.launch {}` safely in a production context.
- Separate concerns across controller, service, and DTO layers.
- Write unit tests for coroutine-based logic using `kotlinx-coroutines-test`.

## ✅ Features

- POST `/orders` endpoint to create an order
- Synchronous and asynchronous process separation
- CoroutineScope injected via Spring configuration
- Unit tests with coroutine test support (`TestScope`)
- Clean, modular project structure

## 🚀 Technologies Used

- Kotlin
- Spring Boot
- Coroutines (`kotlinx.coroutines`)
- JUnit 5
- Mockito Kotlin
- Gradle (Kotlin DSL)

## ▶️ How to Run

Make sure you have JDK 17+ and Gradle installed.

## ▶️ Example Request

POST /orders
Content-Type: application/json

{
  "customerEmail": "client@meli.com",
  "product": "Gaming Mouse"
}
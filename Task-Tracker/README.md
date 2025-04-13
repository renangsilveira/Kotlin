# 🗂️ Task Tracker CLI

A simple Command Line Interface (CLI) application written in Kotlin to help you track and manage your tasks. This project allows you to add, update, delete, and list tasks, as well as mark them as in progress or done. All tasks are stored locally in a JSON file.
A project from Roadmap.sh = https://roadmap.sh/projects/task-tracker


---

## 📌 Features

- Add, update, and delete tasks
- Mark tasks as `in-progress` or `done`
- List tasks by status: `todo`, `in-progress`, `done`
- Persistent storage using a local JSON file
- Clean architecture with separation by Controller, Service, Repository, DTO, and Model layers
- Fully written in pure Kotlin using only standard libraries

---

## 🛠️ Tech Stack

- **Kotlin** (JVM)
- **Gradle** for build automation
- **Standard Kotlin I/O** for file manipulation
- **org.json** for JSON handling

---

## 🚀 How to Run

### Prerequisites

- [JDK 17+](https://adoptopenjdk.net/)
- [Gradle](https://gradle.org/) (optional, can use the wrapper)
- Kotlin compiler installed (optional)
- IntelliJ IDEA recommended

---

### 📦 Build & Run (via Gradle)

From the root of the project:

```bash
# To add a new task
./gradlew run --args="add \"Buy groceries\""

# To update a task
./gradlew run --args="update 1 \"Buy groceries and clean kitchen\""

# To delete a task
./gradlew run --args="delete 1"

# To mark a task as in progress
./gradlew run --args="mark-in-progress 2"

# To mark a task as done
./gradlew run --args="mark-done 2"

# To list all tasks
./gradlew run --args="list"

# To list tasks by status
./gradlew run --args="list done"
./gradlew run --args="list todo"
./gradlew run --args="list in-progress"
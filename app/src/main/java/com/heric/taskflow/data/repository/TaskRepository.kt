package com.heric.taskflow.data.repository

import com.heric.taskflow.data.api.RetrofitInstance
import com.heric.taskflow.data.model.Task
import com.heric.taskflow.data.model.TaskDto
import com.heric.taskflow.data.model.TaskRequest

class TaskRepository {

    suspend fun fetchRemoteTasks(): List<Task> {
        val response = RetrofitInstance.api.getTasks()

        return response.todos.map { dto ->
            dto.toTask()
        }
    }

    suspend fun addRemoteTask(title: String): Task {
        val response = RetrofitInstance.api.addTask(
            TaskRequest(
                todo = title,
                completed = false,
                userId = 1
            )
        )

        return response.toTask()
    }

    suspend fun updateRemoteTask(task: Task): Task {
        val response = RetrofitInstance.api.updateTask(
            task.id,
            TaskRequest(
                todo = task.title,
                completed = task.completed,
                userId = task.userId
            )
        )

        return response.toTask()
    }

    suspend fun deleteRemoteTask(task: Task) {
        RetrofitInstance.api.deleteTask(task.id)
    }
}

fun TaskDto.toTask(): Task {
    return Task(
        id = id,
        title = todo,
        completed = completed,
        userId = userId
    )
}
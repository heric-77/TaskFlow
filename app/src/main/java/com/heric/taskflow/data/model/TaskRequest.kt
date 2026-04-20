package com.heric.taskflow.data.model

data class TaskRequest(
    val todo: String,
    val completed: Boolean,
    val userId: Int
)
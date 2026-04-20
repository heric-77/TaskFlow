package com.heric.taskflow.data.model

data class TaskDto(
    val id: Int,
    val todo: String,
    val completed: Boolean,
    val userId: Int
)
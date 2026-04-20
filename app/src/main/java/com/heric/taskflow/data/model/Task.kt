package com.heric.taskflow.data.model

data class Task(
    val id: Int = 0,
    val title: String,
    val completed: Boolean = false,
    val userId: Int = 1
)
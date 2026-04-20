package com.heric.taskflow.data.api

import com.heric.taskflow.data.model.TaskDto
import com.heric.taskflow.data.model.TaskRequest
import com.heric.taskflow.data.model.TaskResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface TaskApiService {

    @GET("todos")
    suspend fun getTasks(): TaskResponse

    @POST("todos/add")
    suspend fun addTask(
        @Body request: TaskRequest
    ): TaskDto

    @PUT("todos/{id}")
    suspend fun updateTask(
        @Path("id") id: Int,
        @Body request: TaskRequest
    ): TaskDto

    @DELETE("todos/{id}")
    suspend fun deleteTask(
        @Path("id") id: Int
    ): TaskDto
}
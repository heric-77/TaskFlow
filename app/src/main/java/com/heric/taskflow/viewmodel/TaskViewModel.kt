package com.heric.taskflow.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heric.taskflow.data.model.Task
import com.heric.taskflow.data.repository.TaskRepository
import kotlinx.coroutines.launch

class TaskViewModel : ViewModel() {

    private val repository = TaskRepository()

    var tasks = mutableStateListOf<Task>()
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    init {
        loadRemoteTasks()
    }

    fun loadRemoteTasks() {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null

            try {
                val remoteTasks = repository.fetchRemoteTasks()
                tasks.clear()
                tasks.addAll(remoteTasks)
            } catch (e: Exception) {
                errorMessage = "Erro ao carregar tarefas"
                e.printStackTrace()
            } finally {
                isLoading = false
            }
        }
    }

    fun addTask(title: String) {
        viewModelScope.launch {
            try {
                val createdTask = repository.addRemoteTask(title)
                tasks.add(0, createdTask)
            } catch (e: Exception) {
                errorMessage = "Erro ao adicionar tarefa"
                e.printStackTrace()
            }
        }
    }

    fun updateTask(updatedTask: Task) {
        viewModelScope.launch {
            try {
                val remoteTask = repository.updateRemoteTask(updatedTask)

                val index = tasks.indexOfFirst { it.id == updatedTask.id }
                if (index != -1) {
                    tasks[index] = remoteTask
                }
            } catch (e: Exception) {
                errorMessage = "Erro ao atualizar tarefa"
                e.printStackTrace()
            }
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            try {
                repository.deleteRemoteTask(task)
                tasks.removeAll { it.id == task.id }
            } catch (e: Exception) {
                errorMessage = "Erro ao excluir tarefa"
                e.printStackTrace()
            }
        }
    }

    fun clearError() {
        errorMessage = null
    }
}
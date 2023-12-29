package com.sparta.todolist.domain.exception

data class ModelNotFoundException(val modelName: String, val id: Long): RuntimeException(
    "Model $modelName with id $id not found"
)
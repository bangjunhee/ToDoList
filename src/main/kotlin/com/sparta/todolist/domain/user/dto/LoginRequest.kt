package com.sparta.todolist.domain.user.dto

data class LoginRequest(
    val email: String,
    val password: String
)

package com.sparta.todolist.domain.user.dto

data class SignUpRequest(
    val email: String,
    val password: String,
    var username: String
)

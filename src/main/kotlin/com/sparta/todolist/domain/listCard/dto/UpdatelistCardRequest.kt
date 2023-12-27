package com.sparta.todolist.domain.listCard.dto

data class UpdatelistCardRequest(
    val title: String,
    val name: String,
    val content: String
)
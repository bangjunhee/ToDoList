package com.sparta.todolist.domain.listCard.dto

data class UpdatelistCardRequest(
    val title: String,
    val authorName: String,
    val content: String,
    val isDone: String
)
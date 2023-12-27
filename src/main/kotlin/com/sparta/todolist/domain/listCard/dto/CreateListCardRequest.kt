package com.sparta.todolist.domain.listCard.dto

import java.time.LocalDate

data class CreateListCardRequest(
    val title: String,
    val name: String,
    val content: String,
    val date: LocalDate,
    val isDone: Boolean
)
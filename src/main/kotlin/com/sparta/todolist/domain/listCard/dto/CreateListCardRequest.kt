package com.sparta.todolist.domain.listCard.dto

import java.time.LocalDate

data class CreateListCardRequest(
    val title: String,
    val authorName: String,
    val content: String,
    val date: LocalDate
)
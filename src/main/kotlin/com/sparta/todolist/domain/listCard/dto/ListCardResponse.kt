package com.sparta.todolist.domain.listCard.dto

import java.time.LocalDate

data class ListCardResponse(
    val id: Long,
    val title: String,
    val authorName: String,
    val content: String,
    val date: LocalDate,
    val isDone: String
)
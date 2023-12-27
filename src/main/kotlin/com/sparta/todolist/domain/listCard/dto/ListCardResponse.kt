package com.sparta.todolist.domain.listCard.dto

import java.time.LocalDate

data class ListCardResponse(
    val id: Long,
    val title: String,
    val name: String,
    val content: String,
    val date: LocalDate,
    val isDone: Boolean
)
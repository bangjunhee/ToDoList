package com.sparta.todolist.domain.comment.dto

data class CreateCommentRequest (
    val authorName: String,
    val content: String
)

package com.sparta.todolist.domain.comment.dto

data class CommentResponse (
    val id: Long,
    val authorName: String,
    val content: String
)
package com.sparta.todolist.domain.comment.repository

import com.sparta.todolist.domain.comment.model.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository: JpaRepository<Comment, Long> {
    fun findByListCardIdAndId(listCardId: Long, commentId: Long): Comment?
}
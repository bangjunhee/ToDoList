package com.sparta.todolist.domain.comment.service

import com.sparta.todolist.domain.comment.dto.CommentResponse
import com.sparta.todolist.domain.comment.dto.CreateCommentRequest
import com.sparta.todolist.domain.comment.dto.UpdateCommentRequest

interface CommentService {

    // 댓글 가져오기
    fun getAllComments(listCardId: Long): List<CommentResponse>

    // 댓글 생성하기
    fun createComment(listCardId: Long, request: CreateCommentRequest): CommentResponse

    // 댓글 수정
    fun updateComment(commentId: Long, listCardId: Long, request: UpdateCommentRequest): CommentResponse

    //댓글 삭제
    fun deleteComment(commentId: Long, listCardId: Long)

}
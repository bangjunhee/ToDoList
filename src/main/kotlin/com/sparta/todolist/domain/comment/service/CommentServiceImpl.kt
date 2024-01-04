package com.sparta.todolist.domain.comment.service

import com.sparta.todolist.domain.comment.dto.CommentResponse
import com.sparta.todolist.domain.comment.dto.CreateCommentRequest
import com.sparta.todolist.domain.comment.dto.UpdateCommentRequest
import com.sparta.todolist.domain.comment.model.Comment
import com.sparta.todolist.domain.comment.repository.CommentRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import com.sparta.todolist.domain.comment.model.toResponse
import com.sparta.todolist.domain.exception.ModelNotFoundException
import com.sparta.todolist.domain.listCard.repository.ListCardRepository
import org.springframework.data.repository.findByIdOrNull


@Service
class CommentServiceImpl(
    private val commentRepository: CommentRepository,
    private val listCardRepository: ListCardRepository
): CommentService {
    override fun getAllComments(listCardId: Long): List<CommentResponse> {
        // 모든 코멘트 가져오
        return commentRepository.findAll().map { it.toResponse() }

    }

    @Transactional
    override fun createComment(listCardId: Long, request: CreateCommentRequest): CommentResponse {
        val listCard = listCardRepository.findByIdOrNull(listCardId) ?: throw ModelNotFoundException("ListCard", listCardId)

        val comment = Comment(
            authorName = request.authorName,
            content = request.content,
            listCard = listCard
        )
        listCard.addComment(comment)
        listCardRepository.save(listCard)
        return comment.toResponse()
    }

    @Transactional
    override fun updateComment(commentId: Long, listCardId: Long, request: UpdateCommentRequest): CommentResponse {
        val comment = commentRepository.findByListCardIdAndId(listCardId, commentId)
            ?: throw ModelNotFoundException("Comment", commentId)

        val content = request.content
        comment.content = content

        return commentRepository.save(comment).toResponse()
    }

    @Transactional
    override fun deleteComment(commentId: Long, listCardId: Long) {
        val listCard = listCardRepository.findByIdOrNull(listCardId)
            ?: throw ModelNotFoundException("ListCard", listCardId)
        val comment = commentRepository.findByIdOrNull(commentId)
            ?: throw ModelNotFoundException("Comment", commentId)

        listCard.deleteComment(comment)
    }
}
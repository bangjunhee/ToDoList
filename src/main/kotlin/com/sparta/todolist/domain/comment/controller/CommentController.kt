package com.sparta.todolist.domain.comment.controller

import com.sparta.todolist.domain.comment.dto.*
import com.sparta.todolist.domain.comment.service.CommentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
@RequestMapping("/listCards/{listCardId}/comments")
@RestController
class CommentController(
    private val commentService: CommentService
) {

    @GetMapping
    fun getAllComments(@PathVariable listCardId: Long): ResponseEntity<List<CommentResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.getAllComments(listCardId))
    }

    @PostMapping
    fun createComment(
        @PathVariable listCardId: Long,
        @RequestBody createCommentRequest: CreateCommentRequest,
    ):ResponseEntity<CommentResponse>{
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(commentService.createComment(listCardId, createCommentRequest))
    }

    @PutMapping("/{commentId}")
    fun updateComment(
        @PathVariable listCardId: Long,
        @PathVariable commentId: Long,
        @RequestBody updateCommentRequest: UpdateCommentRequest
    ): ResponseEntity<CommentResponse> {
        return ResponseEntity
           .status(HttpStatus.OK)
           .body(commentService.updateComment(commentId, listCardId, updateCommentRequest))
    }

    @DeleteMapping("/{commentId}")
    fun deleteComment(
        @PathVariable listCardId: Long,
        @PathVariable commentId: Long
    ): ResponseEntity<Unit> {
        commentService.deleteComment(commentId, listCardId)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }
}
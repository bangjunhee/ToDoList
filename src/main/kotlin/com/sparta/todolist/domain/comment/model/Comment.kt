package com.sparta.todolist.domain.comment.model

import com.sparta.todolist.domain.comment.dto.CommentResponse
import com.sparta.todolist.domain.listCard.dto.ListCardResponse
import com.sparta.todolist.domain.listCard.model.ListCard
import jakarta.persistence.*

@Entity
@Table(name = "comments")
class Comment(

    @Column(name = "author_name")
    var authorName: String,

    @Column(name = "content")
    var content: String,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "listcard_id", nullable = false)
    var listCard: ListCard
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null


}
fun Comment.toResponse(): CommentResponse {
    return CommentResponse(
        id = id!!,
        authorName = authorName,
        content = content
    )
}
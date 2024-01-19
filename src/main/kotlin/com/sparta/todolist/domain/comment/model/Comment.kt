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
    // 1:N 구조에서 N쪽에 작성하는 코드
    // @joinColumn 어노테이션으로 FK 설정해줌
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
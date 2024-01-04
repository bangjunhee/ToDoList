package com.sparta.todolist.domain.listCard.model

import com.sparta.todolist.domain.comment.model.Comment
import com.sparta.todolist.domain.listCard.dto.ListCardResponse
import jakarta.persistence.*
import java.time.LocalDate


@Entity
@Table(name = "listcards")
class ListCard (
    @Column(name = "title")
    var title: String,

    @Column(name = "author_name")
    var authorName: String,

    @Column(name = "content")
    var content: String,

    @Column(name = "date")
    var date: LocalDate,

    @Enumerated(EnumType.STRING)
    @Column(name = "id_done")
    var isDone: IsDoneStatus,

    @OneToMany(mappedBy = "listCard", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    var comments: MutableList<Comment> = mutableListOf()
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    fun addComment(comment: Comment) {
        comments.add(comment)
    }

    fun deleteComment(comment: Comment){
        comments.remove(comment)
    }


}
fun ListCard.toResponse(): ListCardResponse {
    return ListCardResponse(
        id = id!!,
        title = title,
        authorName = authorName,
        content = content,
        date = date,
        isDone = isDone.name
    )
}
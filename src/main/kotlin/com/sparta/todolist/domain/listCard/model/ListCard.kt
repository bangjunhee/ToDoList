package com.sparta.todolist.domain.listCard.model

import com.sparta.todolist.domain.comment.model.Comment
import com.sparta.todolist.domain.listCard.dto.ListCardResponse
import jakarta.persistence.*
import java.time.LocalDate

// 데이터베이스의 값을 다룰 때 사용하는 엔티티 정의
// @Entity 어노테이션으로 엔티티임을 알림
@Entity
@Table(name = "listcards")
class ListCard (

    // 각 column 에 해당하는 이름을 알려주고 자료형 선언
    @Column(name = "title")
    var title: String,

    @Column(name = "author_name")
    var authorName: String,

    @Column(name = "content")
    var content: String,

    @Column(name = "date")
    var date: LocalDate,

    // 현재 상태를 저장하는 enum class 의 값을 String 으로 받는 어노테이션
    @Enumerated(EnumType.STRING)
    @Column(name = "id_done")
    var isDone: IsDoneStatus,

    // 리스트 카드와 comment 사이 관계는 1:N이다. 리스트 카드 안에 여러개의 댓글이 달릴 수 있기 때문.
    // 이를 위해 FK의 대상이 되는 listcards 모델에  @OneToMany 어노테이션으로 관계 정립
    // mappedBy = "listCard"는 FK의 대상이 되는 모델의 어노테이션에 작성
    @OneToMany(mappedBy = "listCard", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    var comments: MutableList<Comment> = mutableListOf()
){
    // ID는 생성자가 아닌 직접 선언. 값을 사용자가 할당하지 않기 때문
    // @GeneratedValue(strategy = GenerationType.IDENTITY)로 차례대로 값 할당
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    // 댓글을 추가하는 메서드
    // 리스트카드의 댓글은 리스트카드의 모델에 있기 때문에 add 하려면 리스트카드 모델에서 해야함
    fun addComment(comment: Comment) {
        comments.add(comment)
    }

    // 삭제도 마찬가지
    fun deleteComment(comment: Comment){
        comments.remove(comment)
    }


}

// DTO 에 엔티티 값을 넣는 메서드
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
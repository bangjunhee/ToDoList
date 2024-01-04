package com.sparta.todolist.domain.listCard.repository

import com.sparta.todolist.domain.listCard.model.ListCard
import org.springframework.data.jpa.repository.JpaRepository

// JpaRepository<객체 타입, 아이디 타입>
interface ListCardRepository: JpaRepository<ListCard, Long> {

}
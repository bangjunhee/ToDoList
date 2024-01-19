package com.sparta.todolist.domain.listCard.service

import com.sparta.todolist.domain.listCard.dto.CreateListCardRequest
import com.sparta.todolist.domain.listCard.dto.ListCardResponse
import com.sparta.todolist.domain.listCard.dto.UpdatelistCardRequest

// Service Layer 구현
// ListCardService 는 인터페이스 형태로 작성
// controller 에서 이 인터페이스만 생성자로 주입하면 메서드를 일일히 주입하지 않아도 됨
// 메서드 구현은 ListCardServiceImpl 에서!
interface ListCardService {

    fun getAllListCards(): List<ListCardResponse>

    fun getListCardById(listCardId: Long): ListCardResponse

    fun createListCard(request: CreateListCardRequest): ListCardResponse

    fun updateListCard(listCardId: Long, request: UpdatelistCardRequest): ListCardResponse

    fun deleteListCard(listCardId: Long)

}
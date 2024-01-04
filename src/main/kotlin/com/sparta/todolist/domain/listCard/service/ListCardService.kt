package com.sparta.todolist.domain.listCard.service

import com.sparta.todolist.domain.listCard.dto.CreateListCardRequest
import com.sparta.todolist.domain.listCard.dto.ListCardResponse
import com.sparta.todolist.domain.listCard.dto.UpdatelistCardRequest

interface ListCardService {

    fun getAllListCards(): List<ListCardResponse>

    fun getListCardById(listCardId: Long): ListCardResponse

    fun createListCard(request: CreateListCardRequest): ListCardResponse

    fun updateListCard(listCardId: Long, request: UpdatelistCardRequest): ListCardResponse

    fun deleteListCard(listCardId: Long)

}
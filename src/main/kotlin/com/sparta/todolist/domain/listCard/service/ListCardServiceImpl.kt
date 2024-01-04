package com.sparta.todolist.domain.listCard.service

import com.sparta.todolist.domain.exception.ModelNotFoundException
import com.sparta.todolist.domain.listCard.dto.CreateListCardRequest
import com.sparta.todolist.domain.listCard.dto.ListCardResponse
import com.sparta.todolist.domain.listCard.dto.UpdatelistCardRequest
import com.sparta.todolist.domain.listCard.model.ListCard
import com.sparta.todolist.domain.listCard.repository.ListCardRepository
import com.sparta.todolist.domain.listCard.model.toResponse
import com.sparta.todolist.domain.listCard.model.IsDoneStatus
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ListCardServiceImpl(
    private val listCardRepository: ListCardRepository
): ListCardService
{
    override fun getAllListCards(): List<ListCardResponse> {
        return listCardRepository.findAll().map { it.toResponse() }
    }

    override fun getListCardById(listCardId: Long): ListCardResponse {
        val listCard = listCardRepository.findByIdOrNull(listCardId)
            ?: throw ModelNotFoundException("listCardId", listCardId)
        return listCard.toResponse()
    }


    @Transactional
    override fun createListCard(request: CreateListCardRequest): ListCardResponse {
        return listCardRepository.save(
            ListCard(
                title = request.title,
                authorName = request.authorName,
                content = request.content,
                date = request.date,
                isDone = IsDoneStatus.NOT_DONE
            )
        ).toResponse()
    }

    @Transactional
    override fun updateListCard(listCardId: Long, request: UpdatelistCardRequest): ListCardResponse {
        val listcard = listCardRepository.findByIdOrNull(listCardId)
            ?: throw ModelNotFoundException("listCardId", listCardId)
        val (title, authorName, content, isDone) = request

        listcard.title = title
        listcard.authorName = authorName
        listcard.content = content
        try {
            listcard.isDone = IsDoneStatus.valueOf(isDone)
        } catch (e: IllegalArgumentException) {
            println("Invalid isDone value: $isDone. Using 'DONE' or 'NOT_DONE'.")
            listcard.isDone = IsDoneStatus.NOT_DONE
        }

        return listCardRepository.save(listcard).toResponse()
    }

    @Transactional
    override fun deleteListCard(listCardId: Long) {
        val listCard = listCardRepository.findByIdOrNull(listCardId) ?: throw ModelNotFoundException("listCardId", listCardId)
        listCardRepository.delete(listCard)
    }



}
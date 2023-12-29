package com.sparta.todolist.domain.listCard.service

import com.sparta.todolist.domain.exception.ModelNotFoundException
import com.sparta.todolist.domain.listCard.dto.CreateListCardRequest
import com.sparta.todolist.domain.listCard.dto.ListCardResponse
import com.sparta.todolist.domain.listCard.dto.UpdatelistCardRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ListCardServiceImpl: ListCardService {

    override fun getAllListCards(): List<ListCardResponse> {
        // TODO: DB에서 모든 리스트카드 조회하여 ListCardResponse 목록으로 변환
        TODO("Not yet implemented")
    }

    override fun getListCardById(listCardId: Long): ListCardResponse {
        // TODO: DB에서 ID기반으로 리스트카드 조회하여 ListCardResponse 로 변환
        //  listCardId가 없다면 ModelNotFoundException
        // TODO("Not yet implemented")
        throw ModelNotFoundException("listCardId", 1L)
    }


    @Transactional
    override fun createListCard(request: CreateListCardRequest): ListCardResponse {
        // TODO: request 를 ListCard로 변환 후 DB에 저장
        TODO("Not yet implemented")
    }

    @Transactional
    override fun updateListCard(listCardId: Long, request: UpdatelistCardRequest): ListCardResponse {
        // TODO: DB에서 listCardId 에 해당하는 ListCard를 가져온 후 request 기반으로 업데이트 후 DB에 업데이트,
        //  결과를 ListCardResponse 로 변환 후 반환
        //  listCardId가 없다면 ModelNotFoundException
        TODO("Not yet implemented")
    }

    @Transactional
    override fun deleteListCard(listCardId: Long) {
        // TODO: DB에서 listCardId 에 해당하는 ListCard를 가져온 후 삭제
        //  listCardId가 없다면 ModelNotFoundException
        TODO("Not yet implemented")
    }
}
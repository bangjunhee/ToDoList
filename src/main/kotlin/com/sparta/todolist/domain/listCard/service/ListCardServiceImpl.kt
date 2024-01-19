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

// ListCardService 를 구현
// @Service 어노테이션을 사용해서 Spring Bean 등록
@Service

// ListCardRepository 를 생성자로 주입받음
// Service 는 요청에 대한 로직을 구현하는 부분이니까 ListCardRepository 를 이용해 데이터베이스와 통신해야겠지?
class ListCardServiceImpl(
    private val listCardRepository: ListCardRepository
): ListCardService  // 부모가 ListCardService
{

    // 모든 카드를 조회하는 메서드 구현
    // 반환 형태가 List<ListCardResponse>  ->> ListCardResponse DTO 의 리스트 형태
    override fun getAllListCards(): List<ListCardResponse> {
        // listCardRepository 에서 findAll() 메서드 사용
        // 이 후 .map 을 이용해서 리스트를 다시 정의
        // 다시 정의한 리스트 : { it.toResponse() }
        // toResponse()는 Model 부분에서 구현된 메서드
        // 엔티티의 값을 DTO에 넣어주는 역할을 함
        // 즉 { it.toResponse() }은 리스트의 각 원소(엔티티) 를 DTO 에 넣어서 List 로 반환
        return listCardRepository.findAll().map { it.toResponse() }
    }

    // 단건 카드를 조회하는 메서드 구현
    override fun getListCardById(listCardId: Long): ListCardResponse {
        // listCard 변수에 listCardRepository 의 findByIdOrNull 메소드의 결과값을 할당
        // 인자는 getListCardById 메서드의 인자 listCardId 사용
        // 즉 getListCardById 의 인자인 ID를 findByIdOrNull 에 넣어서 결과값 받음
        // Id에 맞는 카드 없으면 예외처리 발생
        val listCard = listCardRepository.findByIdOrNull(listCardId)
            ?: throw ModelNotFoundException("listCardId", listCardId)
        // toResponse() 을 이용해 DTO 로 반환 후 return
        return listCard.toResponse()
    }


    // 카드를 새로 만드는 메서드 구현
    // 카드를 생성하는 과정이므로 Transaction 설정해야함
    // 인자로 Controller 에서 받은 CreateListCardRequest 가 옴
    // 반환 타입은 ListCardResponse DTO
    @Transactional
    override fun createListCard(request: CreateListCardRequest): ListCardResponse {
        // save 를 통해 데이터베이스에 저장
        return listCardRepository.save(
            // ListCard 에 값을 넣어서 엔티티로 저장
            // 마지막에 toResponse()를 이용해 DTO 로 변환 후 반환
            ListCard(
                title = request.title,
                authorName = request.authorName,
                content = request.content,
                date = request.date,
                isDone = IsDoneStatus.NOT_DONE
            )
        ).toResponse()
    }

    // 카드를 업데이트 하는 메서드 구현
    // 마찬가지로 데이터베이스에 접근해서 값을 변경하므로 트랜잭션 설정해줌
    @Transactional
    // 인자로 ID와 UpdateListCardRequest DTO 받음
    // 반환형은 ListCardRequest
    override fun updateListCard(listCardId: Long, request: UpdatelistCardRequest): ListCardResponse {
        // listcard 변수에 findByIdOrNull 를 이용해 ID에 맞는 카드 엔티티 저장
        val listcard = listCardRepository.findByIdOrNull(listCardId)
            ?: throw ModelNotFoundException("listCardId", listCardId)
        // 구조 분헤 선언
        // request 의 변수를 할당함
        val (title, authorName, content, isDone) = request
        // 엔티티에 request 로 받은 정보 저장
        listcard.title = title
        listcard.authorName = authorName
        listcard.content = content
        // 할 일 완료 정보 변경
        // 예외처리로 다른 값 들어올 경우 방지
        try {
            listcard.isDone = IsDoneStatus.valueOf(isDone)
        } catch (e: IllegalArgumentException) {
            println("Invalid isDone value: $isDone. Using 'DONE' or 'NOT_DONE'.")
            listcard.isDone = IsDoneStatus.NOT_DONE
        }
        // 업데이트 된 엔티티를 데이터베이스에 저장 후 DTO 에 넣어 반환
        return listCardRepository.save(listcard).toResponse()
    }

    // 카드 삭제 메소드 구현
    // 마찬가지로 트랜잭션 설정 필수
    @Transactional
    override fun deleteListCard(listCardId: Long) {
        val listCard = listCardRepository.findByIdOrNull(listCardId) ?: throw ModelNotFoundException("listCardId", listCardId)
        listCardRepository.delete(listCard)
    }



}
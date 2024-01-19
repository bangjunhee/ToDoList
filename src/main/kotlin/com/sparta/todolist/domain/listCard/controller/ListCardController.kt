package com.sparta.todolist.domain.listCard.controller

import com.sparta.todolist.domain.listCard.dto.CreateListCardRequest
import com.sparta.todolist.domain.listCard.dto.ListCardResponse
import com.sparta.todolist.domain.listCard.dto.UpdatelistCardRequest
import com.sparta.todolist.domain.listCard.service.ListCardService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


// Web Layer
// Controller 설정
// 클라이언트와 애플리캐이션 사이 응답과 요청을 처리한다.
// CRUD 처리를 포함한다.


// @RestController >> Data 를 응답하기 때문에 RestController 어노테이션 사용
// 해당 어노테이션을 사용해 Spring Bean 등록
// @RequestMapping("/listCards") >> Handler Mapping 에게 어떤 URL 을 사용하는지 알려준다.( Mapping 해준다 )
// ListCard 의 경우 /listCards 가 기본 URL 이므로 @RequestMapping("/listCards") 를 사용하는것
@RequestMapping("/listCards")
@RestController
class ListCardController(
    // ListCard 의 Service 를 생성자로 받음
    private val listCardService: ListCardService
) {

    // 카드 전체 조회
    // 조회하는 거니까 GetMapping 어노테이션 사용
    @GetMapping
    // ResponseEntity >> Response 로 클라이언트에게 주는 엔티티를 담는 DTO 를 정할 수 있다.
    // 여기서는 ListCardResponse 라는 DTO 형식을 리스트 형식으로 몽땅 받아옴
    fun getAllListCards(): ResponseEntity<List<ListCardResponse>> {
        // ResponseEntity 를 return 함
        // status 를 200번 OK로 설정 ( 잘 받았다는 뜻 )
        // body 는 listCardService 의 getAllListCards 메서드의 반환값 들어감
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(listCardService.getAllListCards())
    }

    // 카드 단건 조회
    // 단건 조회 또한 조회니까 @GetMapping 어노테이션 사용
    @GetMapping("/{listCardId}")
    // 하나의 카드만 조회하는 거니까 리스트 중 ID로 구분해야 함
    // 즉 Mapping 할 때 추가 URL 을 알려줌
    // {}로 둘러쌓인 ID를 사용할 땐 메서드 인자에 @PathVariable 어노테이션 사용
    // ResponseEntity 를 이용해 ListCardResponse 라는 DTO 에 엔티티 담음
    fun getListCardById(@PathVariable listCardId: Long): ResponseEntity<ListCardResponse> {
        // ResponseEntity 를 return
        // status 를 200번 OK로 설정 ( 잘 받았다는 뜻 )
        // body 에 listCardService 의 getListCardById 메서드의 반환값 들어감
        // 이 때 body 의 자료형은 ListCardResponse 이어야 함.
        // 메서드 인자로는 PathVariable 인 listCardId 사용
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(listCardService.getListCardById(listCardId))

    }

    // 카드 생성
    // 새 카드 생성이므로 @PostMapping 어노테이션 사용
    // "/ListCards"에 바로 생성하기 때문에 PathVariable 은 없음.
    @PostMapping
    // @RequestBody 어노테이션은 클라이언트에서 요청할 때 주는 정보를 담은 엔티티
    // CreateListCardRequest 라는 DTO 에 저장
    fun createListCard(@RequestBody createListCardRequest: CreateListCardRequest): ResponseEntity<ListCardResponse>{
        // ResponseEntity 를 return
        // status 는 201번 CREATED 로 설정
        // body 에는 listCardService 의 createListCard 메서드에 Request 를 인자로 주고 반환
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(listCardService.createListCard(createListCardRequest))
    }

    // 카드 수정
    // 카드를 수정하는 거니까 @PutMapping 어노테이션 사용
    // 특정 카드를 선택하는 작업이므로 @PathVariable 어노테이션 사용
    // 변경할 정보를 담은 RequestBody 를 UpdatelistCardRequest DTO 에 담아 클라이언트에게서 받음
    @PutMapping("/{listCardId}")
    fun updateListCard(
        @PathVariable listCardId: Long,
        @RequestBody updatelistCardRequest: UpdatelistCardRequest
    ): ResponseEntity<ListCardResponse>{
        // ResponseBody 의 body 에 listCardService 의 updateListCard 메서드 실행
        // 메서드 인자로 ID와 Request DTO 를 줌
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(listCardService.updateListCard(listCardId, updatelistCardRequest))
    }


    // 카드 삭제
    // 삭제이므로 @DeleteMapping 어노테이션을 사용
    // ResponseEntity 에 아무런 DTO를 할당하지 않음
    // 지우는 작업인데 응답해 줄 엔티티가 뭐 없지
    @DeleteMapping("/{listCardId}")
    fun deleteListCard(@PathVariable listCardId: Long): ResponseEntity<Unit>{
        listCardService.deleteListCard(listCardId)
        // ResponseEntity 를 return
        // body는 설정하지 않음. 대신 .build()
        // status 에 204번 NO_CONTENT 코드를 설정
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }




}
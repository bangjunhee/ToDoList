package com.sparta.todolist.domain.listCard.controller

import com.sparta.todolist.domain.listCard.dto.CreateListCardRequest
import com.sparta.todolist.domain.listCard.dto.ListCardResponse
import com.sparta.todolist.domain.listCard.dto.UpdatelistCardRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/listCards")
@RestController
class ListCardController {

    // 카드 전체 조회
    @GetMapping()
    fun getAllListCards(): ResponseEntity<List<ListCardResponse>> {
        TODO()
    }

    // 카드 단건 조회
    @GetMapping("/{listCardId}")
    fun getListCardById(@PathVariable listCardId: Long): ResponseEntity<ListCardResponse> {
        TODO()
    }

    // 카드 생성
    @PostMapping
    fun createListCard(@RequestBody createlistCardRequest: CreateListCardRequest): ResponseEntity<ListCardResponse>{
        TODO()
    }

    // 카드 수정
    @PutMapping("/{listCardId}")
    fun updateListCard(@PathVariable listCardId: Long, @RequestBody updatelistCardRequest: UpdatelistCardRequest): ResponseEntity<ListCardResponse>{
        TODO()
    }

    // 카드 삭제
    @DeleteMapping("/{listCardId}")
    fun deleteListCard(@PathVariable listCardId: Long): ResponseEntity<Unit>{
        TODO()
    }
}
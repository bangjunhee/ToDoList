package com.sparta.todolist.infra.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component

@Aspect
@Component
class TestAop {

    @Around("execution(* com.sparta.todolist.domain.listCard.service.ListCardService.getListCardById(..))")
    fun thisIsAdvice(joinPoint: ProceedingJoinPoint){
        println("AOP START!!")
        joinPoint.proceed()
        println("AOP END!!")
    }
}
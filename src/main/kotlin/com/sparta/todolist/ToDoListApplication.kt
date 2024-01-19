package com.sparta.todolist

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.EnableAspectJAutoProxy

@EnableAspectJAutoProxy
@SpringBootApplication
class ToDoListApplication

fun main(args: Array<String>) {
	runApplication<ToDoListApplication>(*args)
}

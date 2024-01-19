package com.sparta.todolist.domain.exception

data class InvalidCredentialException (
    override val message: String? = "the credential is invalid"
): RuntimeException()
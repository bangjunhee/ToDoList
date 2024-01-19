package com.sparta.todolist.domain.user.service

import com.sparta.todolist.domain.user.dto.*

interface UserService {

    fun signUp(signUpRequest: SignUpRequest) : UserResponse

    fun updateUserProfile(userId: Long, updateUserProfileRequest: UpdateUserProfileRequest) : UserResponse

    fun login(request: LoginRequest): LoginResponse

}
package com.sparta.todolist.domain.user.service

import com.sparta.todolist.domain.user.dto.SignUpRequest
import com.sparta.todolist.domain.user.dto.UpdateUserProfileRequest
import com.sparta.todolist.domain.user.dto.UserResponse

interface UserService {

    fun signUp(signUpRequest: SignUpRequest) : UserResponse

    fun updateUserProfile(userId: Long, updateUserProfileRequest: UpdateUserProfileRequest) : UserResponse

}
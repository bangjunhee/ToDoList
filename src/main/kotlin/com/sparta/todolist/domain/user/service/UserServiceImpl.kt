package com.sparta.todolist.domain.user.service

import com.sparta.todolist.domain.exception.ModelNotFoundException
import com.sparta.todolist.domain.user.dto.SignUpRequest
import com.sparta.todolist.domain.user.dto.UpdateUserProfileRequest
import com.sparta.todolist.domain.user.dto.UserResponse
import com.sparta.todolist.domain.user.model.Profile
import com.sparta.todolist.domain.user.model.User
import com.sparta.todolist.domain.user.model.toResponse
import com.sparta.todolist.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
): UserService {

    @Transactional
    override fun signUp(signUpRequest: SignUpRequest): UserResponse {
        if (userRepository.existsByEmail(signUpRequest.email)) {
            throw IllegalStateException("Email is already in use")
        }

        return userRepository.save(
            User(
                email = signUpRequest.email,
                password = signUpRequest.password,
                profile = Profile(
                    userName = signUpRequest.username
                )
            )
        ).toResponse()
    }

    @Transactional
    override fun updateUserProfile(userId: Long, updateUserProfileRequest: UpdateUserProfileRequest): UserResponse {
        val user = userRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("User", userId)
        user.profile = Profile(
            userName = updateUserProfileRequest.userName
        )

        return userRepository.save(user).toResponse()
    }
}
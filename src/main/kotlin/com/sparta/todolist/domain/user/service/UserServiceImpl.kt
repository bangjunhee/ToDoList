package com.sparta.todolist.domain.user.service

import com.sparta.todolist.domain.exception.InvalidCredentialException
import com.sparta.todolist.domain.exception.ModelNotFoundException
import com.sparta.todolist.domain.user.dto.*
import com.sparta.todolist.domain.user.model.Profile
import com.sparta.todolist.domain.user.model.User
import com.sparta.todolist.domain.user.model.toResponse
import com.sparta.todolist.domain.user.repository.UserRepository
import com.sparta.todolist.infra.security.jwt.JwtPlugin
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtPlugin: JwtPlugin
): UserService {

    @Transactional
    override fun signUp(signUpRequest: SignUpRequest): UserResponse {
        if (userRepository.existsByEmail(signUpRequest.email)) {
            throw IllegalStateException("Email is already in use")
        }

        return userRepository.save(
            User(
                email = signUpRequest.email,
                password = passwordEncoder.encode(signUpRequest.password),
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

    override fun login(request: LoginRequest): LoginResponse {
        val user = userRepository.findByEmail(request.email) ?: throw ModelNotFoundException("User", null)

        if(!passwordEncoder.matches(request.password, user.password)){
            throw InvalidCredentialException()
        }

        return LoginResponse(
            accessToken = jwtPlugin.generateAccessToken(
                subject = user.id.toString(),
                email = user.email,
                role = user.role
            )
        )
    }

}
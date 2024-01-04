package com.sparta.todolist.domain.user.model

import com.sparta.todolist.domain.user.dto.UserResponse
import jakarta.persistence.*

@Entity
@Table(name = "app_user")
class User(
    @Column(name = "email", nullable = false)
    val email: String,

    @Column(name = "password", nullable = false)
    val password: String,

    @Embedded
    var profile: Profile,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}
fun User.toResponse(): UserResponse {
    return UserResponse(
        id = id!!,
        username = profile.userName,
        email = email
    )
}
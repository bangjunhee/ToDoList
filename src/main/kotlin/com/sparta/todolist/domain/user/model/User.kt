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

    // @Embedded 어노테이션으로 Profile 이라는 클래스를 엔티티에 추가
    // Profile 에 어떤 값이 추가되어도 상관 x
    @Embedded
    var profile: Profile,

    @Column(name = "role")
    val role: String = "USER"
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
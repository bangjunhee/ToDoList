package com.sparta.todolist.infra.security

import jakarta.annotation.security.DeclareRoles
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.web.authentication.switchuser.SwitchUserGrantedAuthority

data class UserPrincipal(
    val id: Long,
    val email: String,
    val authority: Collection<GrantedAuthority>
){
    constructor(id: Long, email: String, roles: Set<String>): this(
        id, email, roles.map{SimpleGrantedAuthority("ROLE_")}
    )
}

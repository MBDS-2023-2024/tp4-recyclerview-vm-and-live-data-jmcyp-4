package org.mbds.unice.github.data.model

data class User(
    val id: String,
    val login: String,
    val avatarUrl: String,
    var isActive: Boolean = true
)
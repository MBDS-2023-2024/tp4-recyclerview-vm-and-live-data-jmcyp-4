package org.mbds.unice.github.data.model

import android.icu.text.DateFormat
import java.util.Date

data class User(
    val id: String,
    val login: String,
    val avatarUrl: String,
    var isActive: Boolean = true,
    val creationDate: Long
)
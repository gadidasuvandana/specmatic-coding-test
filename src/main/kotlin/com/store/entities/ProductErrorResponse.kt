package com.store.entities

import java.time.LocalDateTime


data class ProductErrorResponse (
    val timestamp: LocalDateTime,
    val status: Int,
    val error: String,
    val path: String
)
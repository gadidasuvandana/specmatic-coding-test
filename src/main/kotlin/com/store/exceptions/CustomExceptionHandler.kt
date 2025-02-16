package com.store.exceptions

import com.store.entities.ProductErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.LocalDateTime

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(ex: Exception): ResponseEntity<ProductErrorResponse> {
        val errors = when (ex) {
            is MethodArgumentNotValidException -> ex.bindingResult.allErrors.joinToString(", ") {
                it.defaultMessage ?: "Invalid input"
            }

            is IllegalArgumentException -> ex.message ?: "Invalid argument"
            else -> "Unknown error"
        }

        val errorResponse = ProductErrorResponse(
            LocalDateTime.now(),
            HttpStatus.BAD_REQUEST.value(),
            errors,
           "/products"
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }
}
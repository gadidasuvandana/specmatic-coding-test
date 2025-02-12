package com.store.controllers

import com.store.entities.ProductRequest
import com.store.services.ProductService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime


@RestController
class Products(private val productService: ProductService){

    @GetMapping("/products")
    fun getProducts(@RequestParam(required = false) type: String?): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(productService.getProducts(type))
        } catch (e: IllegalArgumentException) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                mapOf(
                    "timestamp" to LocalDateTime.now().toString(),
                    "status" to HttpStatus.BAD_REQUEST.value(),
                    "error" to "Invalid request parameter",
                    "path" to "/products"
                )
            )
        }
    }
    @PostMapping("/products")
    fun createProduct(@RequestBody product: ProductRequest): ResponseEntity<Any> {
        return try {
            val productId = productService.createProduct(product)
            ResponseEntity.status(HttpStatus.CREATED).body(mapOf("id" to productId))
        } catch (e: IllegalArgumentException) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                mapOf(
                    "timestamp" to LocalDateTime.now().toString(),
                    "status" to HttpStatus.BAD_REQUEST.value(),
                    "error" to "Invalid request body",
                    "path" to "/products"
                )
            )
        }
    }
}

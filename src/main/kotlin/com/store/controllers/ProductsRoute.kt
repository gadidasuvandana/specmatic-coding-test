package com.store.controllers

import com.store.entities.ProductErrorResponse
import com.store.entities.ProductRequest
import com.store.entities.ProductsResult
import com.store.services.ProductService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime


@RestController
class ProductsRoute(private val productService: ProductService) {

    @GetMapping("/products")
    fun getProducts(@RequestParam(required = false) type: String?): ResponseEntity<Any> {
        return when (val productsResult = productService.getProducts(type)) {
            is ProductsResult.Success -> ResponseEntity.ok(productsResult.products)
            is ProductsResult.Error -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ProductErrorResponse(
                    LocalDateTime.now(),
                    HttpStatus.BAD_REQUEST.value(),
                    productsResult.message,
                    "/products"
                )
            )
        }
    }

    @PostMapping("/products")
    fun createProducts(@Valid @RequestBody product: ProductRequest): ResponseEntity<Map<String, Int>> {
        val productId = productService.createProduct(product)
        return ResponseEntity.status(HttpStatus.CREATED).body(mapOf("id" to productId))
    }
}

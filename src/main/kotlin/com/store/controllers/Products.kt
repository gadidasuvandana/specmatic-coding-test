package com.store.controllers

import com.store.entities.ProductRequest
import com.store.entities.ProductTypeResult
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
class Products(private val productService: ProductService) {

    @GetMapping("/products")
    fun getProducts(@RequestParam(required = false) type: String?): ResponseEntity<Any> {
        return when(val productsResult = productService.getProducts(type)){
            is ProductTypeResult.Success -> ResponseEntity.ok(productsResult.products)
            is ProductTypeResult.Error -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                mapOf(
                    "timestamp" to LocalDateTime.now().toString(),
                    "status" to HttpStatus.BAD_REQUEST.value(),
                    "error" to productsResult.message,
                    "path" to "/products"
                )
            )
        }
    }

    @PostMapping("/products")
    fun createProduct(@RequestBody product: ProductRequest): ResponseEntity<Any> {
        return when(val validatedProduct= product.validate()){
            is ProductRequest.Validator.Success ->
            {
                val productId = productService.createProduct(product)
                ResponseEntity.status(HttpStatus.CREATED).body(mapOf("id" to productId))}

            is ProductRequest.Validator.Error -> {
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    mapOf(
                        "timestamp" to LocalDateTime.now().toString(),
                        "status" to HttpStatus.BAD_REQUEST.value(),
                        "error" to validatedProduct.message,
                        "path" to "/products"
                    )
                )
            }
        }
    }
}

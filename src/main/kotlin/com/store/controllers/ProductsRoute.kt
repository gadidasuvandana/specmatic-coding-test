package com.store.controllers

import com.store.entities.Product
import com.store.entities.ProductRequest
import com.store.services.ProductService
import jakarta.validation.Valid
import jakarta.validation.constraints.Pattern
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
class ProductsRoute(private val productService: ProductService) {

    @GetMapping("/products")
    fun getProducts(@RequestParam(required = false) @Pattern(regexp = "book|food|gadget|other") type: String?): ResponseEntity<List<Product>> {
        return ResponseEntity.ok(productService.getProducts(type))
    }

    @PostMapping("/products")
    fun createProducts(@Valid @RequestBody product: ProductRequest): ResponseEntity<Map<String, Int>> {
        val productId = productService.createProduct(product)
        return ResponseEntity.status(HttpStatus.CREATED).body(mapOf("id" to productId))
    }
}

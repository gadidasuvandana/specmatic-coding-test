package com.store.services

import com.store.entities.ProductRequest
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test

class ProductServiceTest {

    private val productService = ProductService()

    @Test
    fun createProduct_should_return_product_id_when_called_with_a_valid_request() {
        val productRequest = ProductRequest("NewProduct", "food", 100, 1000)
        val productId = productService.createProduct(productRequest)
        assertEquals(5, productId)
    }

    @Test
    fun getProducts_should_filter_based_on_type() {
        val result = productService.getProducts("food")
        assertEquals(1, (result).size)
    }

    @Test
    fun getProducts_returns_all_products_when_product_type_is_null() {
        val result = productService.getProducts(null)
        assertEquals(4, result.size)
    }
}
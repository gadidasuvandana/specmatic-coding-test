package com.store.controllers

import com.store.entities.Product
import com.store.entities.ProductErrorResponse
import com.store.entities.ProductRequest
import com.store.entities.ProductsResult
import com.store.services.ProductService
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.springframework.http.HttpStatus

class ProductsRouteTest {

    private val productService = mock(ProductService::class.java)
    private val productsRoute = ProductsRoute(productService)

    @Test
    fun getProducts_returns_200_when_called_with_valid_type() {
        val type = "food"
        val products = listOf(Product(123, "Pizza", type, 1000, 100))
        `when`(productService.getProducts(type)).thenReturn(ProductsResult.Success(products))

        val response = productsRoute.getProducts(type)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(products, response.body)
    }

    @Test
    fun getProducts_returns_400_when_called_with_invalid_type() {
        val type = "wrong-type"
        `when`(productService.getProducts(type)).thenReturn(ProductsResult.Error("invalid type"))

        val response = productsRoute.getProducts(type)

        assertEquals(HttpStatus.BAD_REQUEST, response.statusCode)
        assertEquals("invalid type", (response.body as ProductErrorResponse).error)
    }

    @Test
    fun getProducts_returns_200_when_called_with_no_query_param() {
        val type = "food"
        val products = listOf(Product(123, "Pizza", type, 1000, 100))
        `when`(productService.getProducts(null)).thenReturn(ProductsResult.Success(products))

        val response = productsRoute.getProducts(null)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(products, response.body)
    }
    @Test
    fun createProducts_returns_201_when_called_with_a_valid_request() {
        val productRequest = ProductRequest("NewProduct", "food", 100, 1000)
        `when`(productService.createProduct(productRequest)).thenReturn(1)

        val response = productsRoute.createProducts(productRequest)

        assertEquals(HttpStatus.CREATED, response.statusCode)
        assertEquals(mapOf("id" to 1), response.body)
    }
    @Test
    fun createProducts_returns_400_when_called_with_an_invalid_request() {
        val productRequest = ProductRequest("NewProduct", "i-do-not-exist", 100, 1000)

        val response = productsRoute.createProducts(productRequest)

        assertEquals(HttpStatus.BAD_REQUEST, response.statusCode)
        assertEquals("Invalid product type", (response.body as ProductErrorResponse).error)
    }
}
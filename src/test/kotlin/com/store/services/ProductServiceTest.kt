package com.store.services

import com.store.entities.Product
import com.store.entities.ProductRequest
import com.store.repositories.ProductRepository
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
class ProductServiceTest {

    @InjectMocks
    private lateinit var productService: ProductService


    @Mock
    private lateinit var productRepository: ProductRepository


    @Test
    fun `getProducts should return all products from the repository`() {
        val product1 = Product(1, "Product 1", "Type 1", 10, 100)
        val product2 = Product(2, "Product 2", "Type 2", 20, 200)
        val products = listOf(product1, product2)

        `when`(productRepository.findAll()).thenReturn(products)

        val result = productService.getProducts(null)

        assertEquals(2, result.size)

    }
    @Test
    fun `getProducts should filter products by type`() {
        val product1 = Product(1, "Product 1", "other", 10, 100)
        val product2 = Product(2, "Product 2", "other", 20, 200)
        val product3 = Product(2, "Product 3", "food", 20, 200)
        val products = listOf(product1, product2,product3)

        `when`(productRepository.findAll()).thenReturn(products)

        val result = productService.getProducts("other")

        assertEquals(2, result.size)

    }
    @Test
    fun `createProduct should return ID of saved product`() {
        val productRequest = ProductRequest("Test Product", "Test Type", 10, 100)
        val productToSave = Product(name = productRequest.name, type = productRequest.type, inventory = productRequest.inventory, cost = productRequest.cost)
        val savedProduct = Product(id = 1, name = productRequest.name, type = productRequest.type, inventory = productRequest.inventory, cost = productRequest.cost)

        `when`(productRepository.save(productToSave)).thenReturn(savedProduct)

        val returnedId = productService.createProduct(productRequest)

        assertEquals(1, returnedId)
    }
}
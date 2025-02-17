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
    fun `getProductsFromRepo should return all products from the repository`() {
        val product1 = Product(1, "Product 1", "Type 1", 10, 100)
        val product2 = Product(2, "Product 2", "Type 2", 20, 200)
        val products = listOf(product1, product2)

        `when`(productRepository.findAll()).thenReturn(products)

        val result = productService.getProductsFromRepo()

        assertEquals(2, result.size)

    }

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
package com.store.services

import com.store.entities.Product
import com.store.entities.ProductCategory
import com.store.entities.ProductRequest
import org.springframework.stereotype.Service

@Service
class ProductService() {
    private val sampleList = mutableListOf(
        Product(1, "Product1", ProductCategory.FOOD.toLowerCase(), 100),
        Product(2, "Product2", ProductCategory.GADGET.toLowerCase(), 200),
        Product(3, "Product3", ProductCategory.OTHER.toLowerCase(), 300),
        Product(4, "Product4", ProductCategory.BOOK.toLowerCase(), 400)
    )

    fun createProduct(product: ProductRequest): Int {
        if (product.name.isBlank()) {
            throw IllegalArgumentException("Invalid product name")
        }
        if (product.type.isBlank() || !ProductCategory.entries.map { value -> value.toLowerCase() }.contains(product.type)) {
            throw IllegalArgumentException("Invalid product category")
        }
        if (product.inventory !in 1..9999) {
            throw IllegalArgumentException("Invalid product inventory")
        }
        val newProduct = Product((sampleList.size + 1), product.name, product.type, product.inventory)
        sampleList.add(newProduct)
        return newProduct.id
    }

    fun getProducts(type: String? = null): List<Product> {
        return type?.let {
            if(!ProductCategory.entries.map { value -> value.toLowerCase() }.contains(it)) {
                throw IllegalArgumentException("Invalid product category")
            }
            getProductsByCategory(it)
        } ?: getAllProducts()
    }

    private fun getAllProducts(): List<Product> {
        return sampleList
    }

    private fun getProductsByCategory(type: String): List<Product> {
        return sampleList.filter { it.type == type }
    }
}

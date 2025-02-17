package com.store.services

import com.store.entities.Product
import com.store.entities.ProductRequest
import com.store.repositories.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class ProductService {

    @Autowired
    private lateinit var productRepository: ProductRepository

    fun getProducts(type: String?):List<Product>{
        val allProducts = productRepository.findAll()
        return type?.let { allProducts.filter { it.type == type } }?: allProducts
    }

    fun createProduct(product: ProductRequest): Int {
        val newProduct = Product(
            name = product.name,
            type = product.type,
            inventory = product.inventory,
            cost = product.cost
        )
        return productRepository.save(newProduct).id
    }

}

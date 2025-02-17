package com.store.services

import com.store.entities.Product
import com.store.entities.ProductRequest
import com.store.entities.ProductType
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

    private val sampleList = mutableListOf(
        Product(1, "Product1", ProductType.FOOD.toLowerCase(), 100, 1000),
        Product(2, "Product2", ProductType.GADGET.toLowerCase(), 200, 2000),
        Product(3, "Product3", ProductType.OTHER.toLowerCase(), 300, 3000),
        Product(4, "Product4", ProductType.BOOK.toLowerCase(), 400, 4000),
    )

    fun createProduct(product: ProductRequest): Int {
        val newProduct = Product((sampleList.size + 1), product.name, product.type, product.inventory, product.cost)
        sampleList.add(newProduct)
        return newProduct.id
    }
}

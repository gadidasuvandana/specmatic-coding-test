package com.store.entities


sealed class ProductsResult {
    data class Success(val products: List<Product>) : ProductsResult()
    data class Error(val message: String) : ProductsResult()
}
package com.store.entities


sealed class ProductTypeResult {
    data class Success(val products: List<Product>) : ProductTypeResult()
    data class Error(val message: String) : ProductTypeResult()
}
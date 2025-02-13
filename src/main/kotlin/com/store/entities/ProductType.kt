package com.store.entities

enum class ProductType(name: String) {
    BOOK("book"),
    FOOD("food"),
    GADGET("gadget"),
    OTHER("other");
    fun toLowerCase(): String {
        return name.lowercase()
    }
}
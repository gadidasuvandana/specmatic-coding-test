package com.store.entities

import com.fasterxml.jackson.annotation.JsonProperty

data class ProductRequest(
    @JsonProperty("name") val name: String,
    @JsonProperty("type") val type: String,
    @JsonProperty("inventory") val inventory: Int,
    @JsonProperty("cost") val cost: Int?
) {
    sealed class Validator {
        data object Success : Validator()
        data class Error(val message: String) : Validator()
    }

    fun validate(): Validator {
        return when {
            this.name.toIntOrNull() != null -> Validator.Error("Product name cannot be an integer")
            this.name.toBooleanStrictOrNull() != null -> Validator.Error("Product name cannot be a boolean value")
            this.name.isBlank() -> Validator.Error("Invalid product name")
            this.type.isBlank() || !ProductType.entries.map { value -> value.toLowerCase() }
                .contains(this.type) -> Validator.Error("Invalid product type")

            this.inventory !in 1..9999 -> Validator.Error("Invalid product inventory")
            this.cost == null || this.cost < 0 -> Validator.Error("Invalid product cost")
            else -> Validator.Success
        }
    }
}


package com.store.entities

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern

data class ProductRequest(
    @JsonProperty("name")
    @field:NotBlank(message = "Product Name cannot be blank")
    @field:Pattern(
        regexp = "^(?!\\d+$)(?!true|false$).*$",
        message = "Product name cannot be an integer or boolean value"
    )
    val name: String,

    @JsonProperty("type")
    @field:Pattern(
        regexp = "book|food|gadget|other",
        message = "Invalid product type, product type must belong to [book, food, gadget, other]"
    )
    val type: String,

    @JsonProperty("inventory")
    @field:Min(value = 1, message = "Invalid product inventory, inventory must be greater than 0")
    @field:Max(value = 9999, message = "Invalid product inventory,inventory must be less than 10000")
    val inventory: Int,

    @JsonProperty("cost")
    @field:NotNull(message = "Product cost cannot be null")
    @field:Min(value = 0, message = "Invalid product cost,cost must be greater than 0")
    val cost: Int?
)



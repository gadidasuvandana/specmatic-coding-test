package com.store.entities

import com.fasterxml.jackson.annotation.JsonProperty

data class ProductRequest(
    @JsonProperty("name") val name: String,
    @JsonProperty("type") val type: String,
    @JsonProperty("inventory") val inventory: Int,
    @JsonProperty("cost") val cost: Int?
)


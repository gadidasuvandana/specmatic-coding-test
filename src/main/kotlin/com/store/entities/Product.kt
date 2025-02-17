package com.store.entities

import jakarta.persistence.*

@Entity
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int=0,

    @Column(name = "name")
    val name: String,

    @Column(name = "type")
    val type: String,

    @Column(name = "inventory")
    val inventory: Int,

    @Column(name = "cost")
    val cost: Int?
) {
    constructor() : this(0, "", "", 0, 0)
}

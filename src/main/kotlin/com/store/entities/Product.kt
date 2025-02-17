package com.store.entities

import jakarta.persistence.*

@Entity
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,

    @Column(name = "name")
    val name: String,

    @Column(name = "type")
    val type: String,

    @Column(name = "inventory")
    val inventory: Int,

    @Column(name = "cost")
    val cost: Int?
)

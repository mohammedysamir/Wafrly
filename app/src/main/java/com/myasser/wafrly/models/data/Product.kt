package com.myasser.wafrly.models.data

data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val Category: String,
    val image: String,
    val rating: Rating,
)
data class Rating(val rate: Double, val count: Int)
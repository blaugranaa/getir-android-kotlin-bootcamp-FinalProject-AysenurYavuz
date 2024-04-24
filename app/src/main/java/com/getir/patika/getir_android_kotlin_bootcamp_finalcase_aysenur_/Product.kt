package com.getir.patika.getir_android_kotlin_bootcamp_finalcase_aysenur_

data class Product(
    val id: String,
    val name: String,
    val attribute: String,
    val thumbnailURL: String?,
    val imageURL: String,
    val price: Double,
    val priceText: String
)

data class BeveragePack(
    val id: String,
    val name: String,
    val productCount: Int,
    val products: List<Product>
)
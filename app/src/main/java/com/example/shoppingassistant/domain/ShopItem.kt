package com.example.shoppingassistant.domain

import java.util.*

data class ShopItem(
    val name: String,
    val category: String,
    val price: Double,
    val marketName:String,
    val date: Date,
    val lat:Double,
    val lng:Double,
    var id: Int= UNDEFINED_ID
)

{
    companion object {
        const val UNDEFINED_ID = 0
    }
}
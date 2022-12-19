package com.example.shoppingassistant.domain

import java.util.*

data class ShopItem(
    val id: Int= UNDEFINED_ID,
    val name: String,
    val category: String,
    val price: Double,
    val marketName:String,
    val date: Calendar,
    val lat:Double,
    val lng:Double
)

{
    companion object {
        const val UNDEFINED_ID = 0
    }
}
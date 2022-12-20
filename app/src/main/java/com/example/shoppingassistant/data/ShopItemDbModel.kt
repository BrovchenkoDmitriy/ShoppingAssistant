package com.example.shoppingassistant.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "shop_items")
data class ShopItemDbModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val name: String,
    @ColumnInfo(name = "Category")
    val category: String,
    val price: Double,
    val marketName:String,
    val date: Long,
    val lat:Double,
    val lng:Double
)
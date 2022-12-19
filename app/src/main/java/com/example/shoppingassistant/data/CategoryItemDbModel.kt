package com.example.shoppingassistant.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category_items")
data class CategoryItemDbModel(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    val name:String,
    val count:Int,
    val enabled:Boolean
)


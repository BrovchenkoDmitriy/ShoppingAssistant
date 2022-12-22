package com.example.shoppingassistant.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "position_items")
data class PositionItemDbModel(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    val name:String,
    val count:Int,
    val enabled:Boolean
)


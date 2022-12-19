package com.example.shoppingassistant.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ShopItemDao {
    @Query("SELECT * FROM shop_items WHERE category=:category ")
    fun getShopItemList(category: String): List<ShopItemDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addShopItem(shopItemDbModel: ShopItemDbModel)

    @Query("DELETE FROM shop_items WHERE id=:shopItemId")
    fun deleteShopItem(shopItemId: Int)

    @Query("SELECT*FROM shop_items WHERE id=:shopItemId LIMIT 1")
    fun getShopItem(shopItemId: Int): ShopItemDbModel
}
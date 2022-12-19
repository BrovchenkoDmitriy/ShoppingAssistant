package com.example.shoppingassistant.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CategoryItemDao {
    @Query("SELECT * FROM shop_items")
    fun getCategoryItemList(category: String): List<CategoryItemDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCategoryItem(shopItemDbModel: CategoryItemDbModel)

    @Query("DELETE FROM shop_items WHERE id=:shopItemId")
    fun deleteCategoryItem(shopItemId: Int)

    @Query("SELECT*FROM shop_items WHERE id=:shopItemId LIMIT 1")
    fun getCategoryItem(shopItemId: Int): CategoryItemDbModel
}
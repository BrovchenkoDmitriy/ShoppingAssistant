package com.example.shoppingassistant.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CategoryItemDao {
    @Query("SELECT * FROM category_items")
    fun getCategoryItemList(): LiveData<List<CategoryItemDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCategoryItem(categoryItemDbModel: CategoryItemDbModel)

    @Query("DELETE FROM category_items WHERE id=:categoryItemId")
    suspend fun deleteCategoryItem(categoryItemId: Int)

    @Query("SELECT*FROM category_items WHERE id=:categoryItemId LIMIT 1")
    suspend fun getCategoryItem(categoryItemId: Int): CategoryItemDbModel
}
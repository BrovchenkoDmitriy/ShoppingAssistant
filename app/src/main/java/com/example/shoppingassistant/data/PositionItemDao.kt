package com.example.shoppingassistant.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PositionItemDao {
    @Query("SELECT * FROM position_items")
    fun getPositionItemList(): LiveData<List<PositionItemDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPositionItem(positionItemDbModel: PositionItemDbModel)

    @Query("DELETE FROM position_items WHERE id=:positionItemId")
    suspend fun deletePositionItem(positionItemId: Int)

    @Query("SELECT*FROM position_items WHERE id=:positionItemId LIMIT 1")
    suspend fun getPositionItem(positionItemId: Int): PositionItemDbModel
}
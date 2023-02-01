package com.example.shoppingassistant.domain

import androidx.lifecycle.LiveData
import java.io.File

interface Repository {
    suspend fun getShopItem(shopItemId: Int):ShopItem
    suspend fun addShopItem(shopItem: ShopItem)
    suspend fun deleteShopItem(shopItem: ShopItem)
    suspend fun upgradeShopItem(shopItem: ShopItem)
    fun getShopItemList():LiveData<List<ShopItem>>
    fun getPhotoFile(item: ShopItem): File

    suspend fun getPositionItem(positionItemId: Int):PositionItem
    suspend fun addPositionItem(positionItem: PositionItem)
    suspend fun deletePositionItem(positionItem: PositionItem)
    suspend fun upgradePositionItem(positionItem: PositionItem)
    fun getPositionItemList():LiveData<List<PositionItem>>

}
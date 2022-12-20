package com.example.shoppingassistant.domain

import androidx.lifecycle.LiveData

interface Repository {
    suspend fun getShopItem(shopItemId: Int):ShopItem
    suspend fun addShopItem(shopItem: ShopItem)
    suspend fun deleteShopItem(shopItem: ShopItem)
    suspend fun upgradeShopItem(shopItem: ShopItem)
    fun getShopItemList(category:String):LiveData<List<ShopItem>>

    suspend fun getCategoryItem(categoryItemId: Int):CategoryItem
    suspend fun addCategoryItem(categoryItem: CategoryItem)
    suspend fun deleteCategoryItem(categoryItem: CategoryItem)
    suspend fun upgradeCategoryItem(categoryItem: CategoryItem)
    fun getCategoryItemList():LiveData<List<CategoryItem>>
}
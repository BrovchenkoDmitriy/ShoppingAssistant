package com.example.shoppingassistant.domain

interface Repository {
    fun getShopItem(shopItemId: Int):ShopItem
    fun addShopItem(shopItem: ShopItem)
    fun deleteShopItem(shopItem: ShopItem)
    fun upgradeShopItem(shopItem: ShopItem)
   // fun getShopItemList():List<ShopItem>
    fun getShopItemList(category:String):List<ShopItem>

    fun getCategoryItem(categoryItemId: Int):CategoryItem
    fun addCategoryItem(categoryItem: CategoryItem)
    fun deleteCategoryItem(categoryItem: CategoryItem)
    fun upgradeCategoryItem(categoryItem: CategoryItem)
    fun getCategoryItemList():List<CategoryItem>
}
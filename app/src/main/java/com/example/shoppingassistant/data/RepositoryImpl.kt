package com.example.shoppingassistant.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.shoppingassistant.data.mappers.CategoryItemMapper
import com.example.shoppingassistant.data.mappers.ShopItemMapper
import com.example.shoppingassistant.domain.CategoryItem
import com.example.shoppingassistant.domain.Repository
import com.example.shoppingassistant.domain.ShopItem

class RepositoryImpl(
    private val shopItemDao: ShopItemDao,
    private val categoryItemDao: CategoryItemDao,
    private val shopItemMapper: ShopItemMapper,
    private val categoryItemMapper: CategoryItemMapper
):Repository {
    override suspend fun getShopItem(shopItemId: Int): ShopItem {
        val shopItemDbModel = shopItemDao.getShopItem(shopItemId)
       return shopItemMapper.mapDbModelToEntity(shopItemDbModel)
    }

    override suspend fun addShopItem(shopItem: ShopItem) {
        val shopItemDbModel = shopItemMapper.mapEntityToDbModel(shopItem)
        shopItemDao.addShopItem(shopItemDbModel)
    }

    override suspend fun deleteShopItem(shopItem: ShopItem) {
        val shopItemDbModel = shopItemMapper.mapEntityToDbModel(shopItem)
        shopItemDao.deleteShopItem(shopItemDbModel.id)
    }

    override suspend fun upgradeShopItem(shopItem: ShopItem) {
        val shopItemDbModel = shopItemMapper.mapEntityToDbModel(shopItem)
        shopItemDao.addShopItem(shopItemDbModel)
    }

    override fun getShopItemList(category: String): LiveData<List<ShopItem>> {
        val shopItemDbModelList = shopItemDao.getShopItemList(category)
        return Transformations.map(shopItemDbModelList){
            shopItemMapper.mapDbModelListToEntityList(it)
        }
    }



    override suspend fun getCategoryItem(categoryItemId: Int): CategoryItem {
        val categoryItemDbModel = categoryItemDao.getCategoryItem(categoryItemId)
        return categoryItemMapper.mapDbModelToEntity(categoryItemDbModel)
    }

    override suspend fun addCategoryItem(categoryItem: CategoryItem) {
        val categoryItemDbModel = categoryItemMapper.mapEntityToDbModel(categoryItem)
        categoryItemDao.addCategoryItem(categoryItemDbModel)
    }

    override suspend fun deleteCategoryItem(categoryItem: CategoryItem) {
       val categoryItemDbModel = categoryItemMapper.mapEntityToDbModel(categoryItem)
        categoryItemDao.deleteCategoryItem(categoryItemDbModel.id)
    }

    override suspend fun upgradeCategoryItem(categoryItem: CategoryItem) {
        val categoryItemDbModel = categoryItemMapper.mapEntityToDbModel(categoryItem)
        categoryItemDao.addCategoryItem(categoryItemDbModel)
    }

    override fun getCategoryItemList(): LiveData<List<CategoryItem>> {
        val categoryItemDbModelList = categoryItemDao.getCategoryItemList()
        return Transformations.map(categoryItemDbModelList){
            categoryItemMapper.mapDbModelListToEntityList(it)
        }
    }
}
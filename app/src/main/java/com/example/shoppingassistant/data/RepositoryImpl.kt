package com.example.shoppingassistant.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.shoppingassistant.data.mappers.PositionItemMapper
import com.example.shoppingassistant.data.mappers.ShopItemMapper
import com.example.shoppingassistant.domain.PositionItem
import com.example.shoppingassistant.domain.Repository
import com.example.shoppingassistant.domain.ShopItem

class RepositoryImpl(application: Application) :Repository {

    private val shopItemDao =  AppDataBase.getInstance(application).shopItemDao()
    private val positionItemDao = AppDataBase.getInstance(application).positionItemDao()
    private val shopItemMapper = ShopItemMapper()
    private val positionItemMapper = PositionItemMapper()


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



    override suspend fun getPositionItem(positionItemId: Int): PositionItem {
        val positionItemDbModel = positionItemDao.getPositionItem(positionItemId)
        return positionItemMapper.mapDbModelToEntity(positionItemDbModel)
    }

    override suspend fun addPositionItem(positionItem: PositionItem) {
        val positionItemDbModel = positionItemMapper.mapEntityToDbModel(positionItem)
        positionItemDao.addPositionItem(positionItemDbModel)
    }

    override suspend fun deletePositionItem(positionItem: PositionItem) {
       val positionItemDbModel = positionItemMapper.mapEntityToDbModel(positionItem)
        positionItemDao.deletePositionItem(positionItemDbModel.id)
    }

    override suspend fun upgradePositionItem(positionItem: PositionItem) {
        val positionItemDbModel = positionItemMapper.mapEntityToDbModel(positionItem)
        positionItemDao.addPositionItem(positionItemDbModel)
    }

    override fun getPositionItemList(): LiveData<List<PositionItem>> {
        val positionItemDbModelList = positionItemDao.getPositionItemList()
        return Transformations.map(positionItemDbModelList){
            positionItemMapper.mapDbModelListToEntityList(it)
        }
    }
}
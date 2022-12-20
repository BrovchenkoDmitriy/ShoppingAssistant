package com.example.shoppingassistant.data.mappers

import com.example.shoppingassistant.data.ShopItemDbModel
import com.example.shoppingassistant.domain.ShopItem
import java.util.*

class ShopItemMapper {

    fun mapEntityToDbModel(shopItem: ShopItem): ShopItemDbModel {
        return ShopItemDbModel(
            id = shopItem.id,
            name = shopItem.name,
            category = shopItem.category,
            price = shopItem.price,
            marketName = shopItem.marketName,
            date = shopItem.date.time,
            lat = shopItem.lat,
            lng = shopItem.lng
        )
    }

    fun mapDbModelToEntity(shopItemDbModel: ShopItemDbModel): ShopItem {
        return ShopItem(
            name = shopItemDbModel.name,
            category = shopItemDbModel.category,
            price = shopItemDbModel.price,
            marketName = shopItemDbModel.marketName,
            date = Date(shopItemDbModel.date),
            lat = shopItemDbModel.lat,
            lng = shopItemDbModel.lng,
            id = shopItemDbModel.id,
        )
    }

    fun mapDbModelListToEntityList(list: List<ShopItemDbModel>): List<ShopItem> {
        return list.map {
            mapDbModelToEntity(it)
        }
    }
}
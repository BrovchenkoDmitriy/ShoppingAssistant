package com.example.shoppingassistant.domain.shopItemUseCases

import com.example.shoppingassistant.domain.Repository
import com.example.shoppingassistant.domain.ShopItem

class AddShopItemUseCase(private val repository: Repository) {
    suspend fun addShopItem(shopItem: ShopItem){
        repository.addShopItem(shopItem)
    }
}
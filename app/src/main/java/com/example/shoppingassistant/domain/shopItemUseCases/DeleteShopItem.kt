package com.example.shoppingassistant.domain.shopItemUseCases

import com.example.shoppingassistant.domain.Repository
import com.example.shoppingassistant.domain.ShopItem

class DeleteShopItem(private val repository: Repository) {
    suspend fun deleteShopItem(shopItem: ShopItem){
        repository.deleteShopItem(shopItem)
    }
}
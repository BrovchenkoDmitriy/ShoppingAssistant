package com.example.shoppingassistant.domain.shopItemUseCases

import com.example.shoppingassistant.domain.Repository
import com.example.shoppingassistant.domain.ShopItem

class GetShopItemUseCase(private val repository: Repository){
    suspend fun getShopItem(id:Int): ShopItem {
        return repository.getShopItem(id)
    }
}
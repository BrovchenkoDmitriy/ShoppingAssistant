package com.example.shoppingassistant.domain.shopItemUseCases

import androidx.lifecycle.LiveData
import com.example.shoppingassistant.domain.Repository
import com.example.shoppingassistant.domain.ShopItem

class GetShopItemListUseCase(private val repository: Repository) {
    fun getShopItemList():LiveData<List<ShopItem>>{
        return repository.getShopItemList()
    }
}
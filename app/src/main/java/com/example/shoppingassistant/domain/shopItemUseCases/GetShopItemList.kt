package com.example.shoppingassistant.domain.shopItemUseCases

import androidx.lifecycle.LiveData
import com.example.shoppingassistant.domain.Repository
import com.example.shoppingassistant.domain.ShopItem

class GetShopItemList(private val repository: Repository) {
    fun getShopItemList(category:String):LiveData<List<ShopItem>>{
        return repository.getShopItemList(category)
    }
}
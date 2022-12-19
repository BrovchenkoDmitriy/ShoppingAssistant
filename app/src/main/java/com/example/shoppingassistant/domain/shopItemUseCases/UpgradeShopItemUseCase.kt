package com.example.shoppingassistant.domain.shopItemUseCases

import com.example.shoppingassistant.domain.Repository
import com.example.shoppingassistant.domain.ShopItem

class UpgradeShopItemUseCase(private val repository: Repository) {
    fun upgradeShopItem(shopItem: ShopItem){
        repository.upgradeShopItem(shopItem)
    }
}
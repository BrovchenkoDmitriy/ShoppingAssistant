package com.example.shoppingassistant.domain.categoryItemUseCases

import com.example.shoppingassistant.domain.PositionItem
import com.example.shoppingassistant.domain.Repository

class UpgradePositionItemUseCase(private val repository: Repository) {
    suspend fun upgradePositionItem(positionItem: PositionItem){
        repository.upgradePositionItem(positionItem)
    }
}
package com.example.shoppingassistant.domain.positionItemUseCases

import com.example.shoppingassistant.domain.PositionItem
import com.example.shoppingassistant.domain.Repository

class AddPositionItemUseCase(private val repository: Repository) {
    suspend fun addPositionItem(positionItem: PositionItem){
        repository.addPositionItem(positionItem)
    }
}
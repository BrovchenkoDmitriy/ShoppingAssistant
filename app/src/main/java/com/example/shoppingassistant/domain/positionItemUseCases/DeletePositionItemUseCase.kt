package com.example.shoppingassistant.domain.positionItemUseCases

import com.example.shoppingassistant.domain.PositionItem
import com.example.shoppingassistant.domain.Repository

class DeletePositionItemUseCase(private val repository: Repository) {
    suspend fun deletePositionItem(positionItem: PositionItem){
        repository.deletePositionItem(positionItem)
    }
}

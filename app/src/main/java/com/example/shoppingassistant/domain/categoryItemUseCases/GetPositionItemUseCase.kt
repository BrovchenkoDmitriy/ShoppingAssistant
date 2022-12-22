package com.example.shoppingassistant.domain.categoryItemUseCases

import com.example.shoppingassistant.domain.PositionItem
import com.example.shoppingassistant.domain.Repository

class GetPositionItemUseCase(private val repository: Repository) {
    suspend fun getPositionItem(id: Int):PositionItem{
        return repository.getPositionItem(id)
    }
}
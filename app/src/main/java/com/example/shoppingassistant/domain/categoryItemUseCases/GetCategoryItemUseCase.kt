package com.example.shoppingassistant.domain.categoryItemUseCases

import com.example.shoppingassistant.domain.CategoryItem
import com.example.shoppingassistant.domain.Repository

class GetCategoryItemUseCase(private val repository: Repository) {
    suspend fun getCategoryItem(id: Int):CategoryItem{
        return repository.getCategoryItem(id)
    }
}
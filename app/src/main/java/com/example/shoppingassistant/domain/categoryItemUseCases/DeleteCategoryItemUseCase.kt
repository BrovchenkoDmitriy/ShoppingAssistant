package com.example.shoppingassistant.domain.categoryItemUseCases

import com.example.shoppingassistant.domain.CategoryItem
import com.example.shoppingassistant.domain.Repository

class DeleteCategoryItemUseCase(private val repository: Repository) {
    suspend fun deleteCategoryItem(categoryItem: CategoryItem){
        repository.deleteCategoryItem(categoryItem)
    }
}
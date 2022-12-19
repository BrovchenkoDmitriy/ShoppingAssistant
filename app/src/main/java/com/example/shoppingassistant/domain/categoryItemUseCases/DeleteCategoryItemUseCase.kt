package com.example.shoppingassistant.domain.categoryItemUseCases

import com.example.shoppingassistant.domain.CategoryItem
import com.example.shoppingassistant.domain.Repository

class DeleteCategoryItemUseCase(private val repository: Repository) {
    fun deleteCategoryItem(categoryItem: CategoryItem){
        repository.deleteCategoryItem(categoryItem)
    }
}
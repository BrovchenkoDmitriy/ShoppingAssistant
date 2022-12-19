package com.example.shoppingassistant.domain.categoryItemUseCases

import com.example.shoppingassistant.domain.CategoryItem
import com.example.shoppingassistant.domain.Repository

class AddCategoryItemUseCase(private val repository: Repository) {
    fun addCategoryItem(categoryItem: CategoryItem){
        repository.addCategoryItem(categoryItem)
    }
}
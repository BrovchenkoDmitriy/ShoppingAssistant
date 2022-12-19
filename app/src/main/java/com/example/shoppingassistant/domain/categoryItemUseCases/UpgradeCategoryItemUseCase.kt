package com.example.shoppingassistant.domain.categoryItemUseCases

import com.example.shoppingassistant.domain.CategoryItem
import com.example.shoppingassistant.domain.Repository

class UpgradeCategoryItemUseCase(private val repository: Repository) {
    fun upgradeCategoryItem(categoryItem: CategoryItem){
        repository.upgradeCategoryItem(categoryItem)
    }
}
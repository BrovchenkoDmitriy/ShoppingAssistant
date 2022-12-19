package com.example.shoppingassistant.domain.categoryItemUseCases

import com.example.shoppingassistant.domain.CategoryItem
import com.example.shoppingassistant.domain.Repository

class GetCategoryItemListUseCase(private val repository: Repository) {
    fun getCategoryItemList():List<CategoryItem>{
        return repository.getCategoryItemList()
    }
}
package com.example.shoppingassistant.domain.categoryItemUseCases

import androidx.lifecycle.LiveData
import com.example.shoppingassistant.domain.CategoryItem
import com.example.shoppingassistant.domain.Repository

class GetCategoryItemListUseCase(private val repository: Repository) {
    fun getCategoryItemList():LiveData<List<CategoryItem>>{
        return repository.getCategoryItemList()
    }
}
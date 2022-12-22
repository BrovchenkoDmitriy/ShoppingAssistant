package com.example.shoppingassistant.domain.categoryItemUseCases

import androidx.lifecycle.LiveData
import com.example.shoppingassistant.domain.PositionItem
import com.example.shoppingassistant.domain.Repository

class GetPositionItemListUseCase(private val repository: Repository) {
    fun getPositionItemList():LiveData<List<PositionItem>>{
        return repository.getPositionItemList()
    }
}
package com.example.shoppingassistant.presentation.shoppingList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingassistant.data.RepositoryImpl
import com.example.shoppingassistant.domain.PositionItem
import com.example.shoppingassistant.domain.positionItemUseCases.DeletePositionItemUseCase
import com.example.shoppingassistant.domain.positionItemUseCases.GetPositionItemListUseCase
import com.example.shoppingassistant.domain.positionItemUseCases.UpgradePositionItemUseCase
import kotlinx.coroutines.launch

class ShoppingListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RepositoryImpl(application)

    private val getPositionItemListUseCase = GetPositionItemListUseCase(repository)
    private val upgradePositionItemUseCase = UpgradePositionItemUseCase(repository)
    private val deletePositionItemUseCase = DeletePositionItemUseCase(repository)


    val positionItemList = getPositionItemListUseCase.getPositionItemList()

    fun deletePositionItem(positionItem: PositionItem){
        viewModelScope.launch {
            deletePositionItemUseCase.deletePositionItem(positionItem)
        }
    }

    fun upgradePositionItem(positionItem: PositionItem){
        viewModelScope.launch {
            val newPositionItem = positionItem.copy(enabled = !positionItem.enabled)
            upgradePositionItemUseCase.upgradePositionItem(newPositionItem)
        }
    }



}
package com.example.shoppingassistant.presentation.noteList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingassistant.data.RepositoryImpl
import com.example.shoppingassistant.domain.ShopItem
import com.example.shoppingassistant.domain.shopItemUseCases.DeleteShopItemUseCase
import com.example.shoppingassistant.domain.shopItemUseCases.GetShopItemListUseCase
import kotlinx.coroutines.launch

class NoteListViewModel(application: Application): AndroidViewModel(application) {

    val repository = RepositoryImpl(application)
    private val getNoteItemListUseCase = GetShopItemListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)

    val noteItemList = getNoteItemListUseCase.getShopItemList()

    fun deleteNoteItem(noteItem: ShopItem){
        viewModelScope.launch {
            deleteShopItemUseCase.deleteShopItem(noteItem)
        }
    }


}
package com.example.shoppingassistant.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shoppingassistant.data.RepositoryImpl
import com.example.shoppingassistant.domain.PositionItem
import com.example.shoppingassistant.domain.positionItemUseCases.AddPositionItemUseCase
import com.example.shoppingassistant.domain.positionItemUseCases.GetPositionItemUseCase
import com.example.shoppingassistant.domain.positionItemUseCases.UpgradePositionItemUseCase
import kotlinx.coroutines.launch

class PositionItemViewModel(application: Application) : AndroidViewModel(application) {

    val repository = RepositoryImpl(application)
    private val upgradeShopItemUseCase = UpgradePositionItemUseCase(repository)
    private val addPositionItemUseCase = AddPositionItemUseCase(repository)
    private val getPositionItemUseCase = GetPositionItemUseCase(repository)

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private val _errorInputCount = MutableLiveData<Boolean>()
    val errorInputCount: LiveData<Boolean>
        get() = _errorInputCount

    private val _positionItem = MutableLiveData<PositionItem>()
    val positionItem: LiveData<PositionItem>
        get() = _positionItem

    private val _closePositionItemScreen = MutableLiveData<Unit>()
    val closePositionItemScreen: LiveData<Unit>
        get() = _closePositionItemScreen


    fun getPositionItem(positionItemID: Int) {
        viewModelScope.launch {
            val item = getPositionItemUseCase.getPositionItem(positionItemID)
            _positionItem.value = item
        }


    }

    fun addPositionItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)
        if (fieldsValid) {
            viewModelScope.launch {
                addPositionItemUseCase.addPositionItem(PositionItem(name, count, true))
                finish()
            }
        }
    }

    fun upgradePositionItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)
        if (fieldsValid) {
            _positionItem.value?.let {
                viewModelScope.launch {
                    val item = it.copy(name = name, count = count)
                    upgradeShopItemUseCase.upgradePositionItem(item)
                    finish()
                }
            }
        }
    }


    private fun parseName(name: String?): String {
        return name?.trim() ?: ""
    }

    private fun parseCount(inputCount: String?): Int {
        return try {
            inputCount?.trim()?.toInt() ?: 0
        } catch (exception: Exception) {
            0
        }
    }

    private fun validateInput(name: String, count: Int): Boolean {
        var result = true
        if (name.isBlank()) {
            _errorInputName.value = true
            result = false
        }
        if (count <= 0) {
            _errorInputCount.value = true
            result = false
        }
        return result
    }

    private fun finish() {
        _closePositionItemScreen.value = Unit
    }

    fun resetInputNameError() {
        _errorInputName.value = false
    }

    fun resetInputCountError() {
        _errorInputCount.value = false
    }

}
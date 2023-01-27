package com.example.shoppingassistant.presentation.noteItem

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shoppingassistant.data.RepositoryImpl
import com.example.shoppingassistant.domain.PositionItem
import com.example.shoppingassistant.domain.ShopItem
import com.example.shoppingassistant.domain.shopItemUseCases.AddShopItemUseCase
import com.example.shoppingassistant.domain.shopItemUseCases.GetShopItemUseCase
import com.example.shoppingassistant.domain.shopItemUseCases.UpgradeShopItemUseCase
import kotlinx.coroutines.launch
import java.util.*

class NoteItemViewModel(application: Application):AndroidViewModel(application) {

    val repository = RepositoryImpl(application)
    private val upgradeShopItemUseCase = UpgradeShopItemUseCase(repository)
    private val addShopItemUseCase = AddShopItemUseCase(repository)
    private val getShopItemUseCase = GetShopItemUseCase(repository)

    private val _shopItem = MutableLiveData<ShopItem>()
    val shopItem: LiveData<ShopItem>
        get() = _shopItem

    private val _closeShopItemScreen = MutableLiveData<Unit>()
    val closeShopItemScreen: LiveData<Unit>
        get() = _closeShopItemScreen

    private val _errorInputData = MutableLiveData<Boolean>()
    val errorInputData: LiveData<Boolean>
        get() = _errorInputData


    fun getShopItem(shopItemID: Int) {
        viewModelScope.launch {
            val item = getShopItemUseCase.getShopItem(shopItemID)
            _shopItem.value = item
        }


    }

    fun addShopItem(
        inputName: String?,
        inputCategory: String?,
        inputPrice: Double?,
        inputMarketName: String?,
        inputDate: Long,
        lat:Double,
        lng:Double
    ) {
        val name = parseName(inputName)
        val category = parseCategory(inputCategory)
        val price = parsePrice(inputPrice)
        val marketName = parseMarketName(inputMarketName)
        val fieldsValid = validateInput(name, category, price, marketName)
        val date = Date(inputDate)

        if (fieldsValid) {
            viewModelScope.launch {
                addShopItemUseCase.addShopItem(ShopItem(name, category, price,marketName, date, lat, lng))
                //добавить метод закрытия фрагмента
                finish()
            }
        }
    }

    fun upgradeShopItem(
        inputName: String?,
        inputCategory: String?,
        inputPrice: Double?,
        inputMarketName: String?
    ) {
        val name = parseName(inputName)
        val category = parseCategory(inputCategory)
        val price = parsePrice(inputPrice)
        val marketName = parseMarketName(inputMarketName)
        val fieldsValid = validateInput(name, category, price, marketName)
        if (fieldsValid) {
            _shopItem.value?.let {
                viewModelScope.launch {
                    val item = it.copy(name = name, category = category, price = price, marketName = marketName)
                    upgradeShopItemUseCase.upgradeShopItem(item)
                    //добавить метод закрытия фрагмента
                    finish()
                }
            }
        }
    }

    private fun validateInput(name: String, category: String, price:Double, marketName:String): Boolean {
        var result = true
        if (name.isBlank()) {
            _errorInputData.value = true
            result = false
        }
        if (category.isBlank()) {
            _errorInputData.value = true
            result = false
        }
        if (marketName.isBlank()) {
            _errorInputData.value = true
            result = false
        }
        if ((price*100).toInt() <= 0) {
            _errorInputData.value = true
            result = false
        }
        return result
    }


    private fun parseName(name: String?): String {
        return name?.trim() ?: ""
    }

    private fun parseCategory(category: String?): String {
        return category?.trim() ?: ""
    }

    private fun parsePrice(inputCount: Double?): Double{
        return try {
            inputCount?.toString()?.trim()?.toDouble() ?: 0.0
        } catch (exception: Exception) {
            0.0
        }
    }
    private fun parseMarketName(marketName: String?): String {
        return marketName?.trim() ?: ""
    }

    private fun finish() {
        _closeShopItemScreen.value = Unit
    }



}
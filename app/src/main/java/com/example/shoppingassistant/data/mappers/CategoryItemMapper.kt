package com.example.shoppingassistant.data.mappers

import com.example.shoppingassistant.data.CategoryItemDbModel
import com.example.shoppingassistant.domain.CategoryItem

class CategoryItemMapper {

    fun mapEntityToDbModel(categoryItem: CategoryItem): CategoryItemDbModel {
        return CategoryItemDbModel(
            id = categoryItem.id,
            name = categoryItem.name,
            count = categoryItem.count,
            enabled = categoryItem.enabled
        )
    }

    fun mapDbModelToEntity(categoryItemDbModel: CategoryItemDbModel): CategoryItem {
        return CategoryItem(
            name = categoryItemDbModel.name,
            count = categoryItemDbModel.count,
            enabled = categoryItemDbModel.enabled,
            id = categoryItemDbModel.id
        )
    }

    fun mapDbModelListToEntityList(list: List<CategoryItemDbModel>): List<CategoryItem> {
        return list.map {
            mapDbModelToEntity(it)
        }
    }
}
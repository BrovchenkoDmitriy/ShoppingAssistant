package com.example.shoppingassistant.data.mappers

import com.example.shoppingassistant.data.PositionItemDbModel
import com.example.shoppingassistant.domain.PositionItem

class PositionItemMapper {

    fun mapEntityToDbModel(positionItem: PositionItem): PositionItemDbModel {
        return PositionItemDbModel(
            id = positionItem.id,
            name = positionItem.name,
            count = positionItem.count,
            enabled = positionItem.enabled
        )
    }

    fun mapDbModelToEntity(positionItemDbModel: PositionItemDbModel): PositionItem {
        return PositionItem(
            name = positionItemDbModel.name,
            count = positionItemDbModel.count,
            enabled = positionItemDbModel.enabled,
            id = positionItemDbModel.id
        )
    }

    fun mapDbModelListToEntityList(list: List<PositionItemDbModel>): List<PositionItem> {
        return list.map {
            mapDbModelToEntity(it)
        }
    }
}
package com.example.shoppingassistant.presentation.shoppingList.shoppingListRecycler

import androidx.recyclerview.widget.DiffUtil
import com.example.shoppingassistant.domain.PositionItem

class PositionItemDiffCallBack:DiffUtil.ItemCallback<PositionItem>(){
    override fun areItemsTheSame(oldItem: PositionItem, newItem: PositionItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PositionItem, newItem: PositionItem): Boolean {
        return oldItem == newItem
    }

}
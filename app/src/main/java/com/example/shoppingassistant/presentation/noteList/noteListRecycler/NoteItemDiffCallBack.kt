package com.example.shoppingassistant.presentation.noteList.noteListRecycler

import androidx.recyclerview.widget.DiffUtil
import com.example.shoppingassistant.domain.ShopItem

class NoteItemDiffCallBack:DiffUtil.ItemCallback<ShopItem>(){
    override fun areItemsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem == newItem
    }

}
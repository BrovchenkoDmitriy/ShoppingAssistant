package com.example.shoppingassistant.presentation.shoppingList.shoppingListRecycler

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingassistant.R

class PositionItemViewHolder(val view: View) :
    RecyclerView.ViewHolder(view) {

    val tvPositionItemName: TextView = view.findViewById(R.id.tv_position_item_name)
    val tvPositionItemCount: TextView = view.findViewById(R.id.tv_position_item_count)
}
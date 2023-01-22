package com.example.shoppingassistant.presentation.shoppingList.shoppingListRecycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.shoppingassistant.R
import com.example.shoppingassistant.domain.PositionItem

class PositionListAdapter :
    ListAdapter<PositionItem, PositionItemViewHolder>(PositionItemDiffCallBack()) {

    var onPositionItemLongClickListener: ((PositionItem) -> Unit)? = null
    var onPositionItemClickListener: ((PositionItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PositionItemViewHolder {
        val layout = when (viewType) {
            NOT_PURCHASED_TYPE -> {
                R.layout.position_item
            }
            PURCHASED_TYPE -> {
                R.layout.position_item_purchased
            }
            else -> throw RuntimeException("Unknown view type: $viewType")
        }

        val view = LayoutInflater.from(parent.context).inflate(layout,parent,false)
        return PositionItemViewHolder(view)
    }

    //привязываем к вьюшкам информацию
    override fun onBindViewHolder(viewHolder: PositionItemViewHolder, position: Int) {
        val positionItem = getItem(position)

        viewHolder.tvPositionItemName.text = positionItem.name
        viewHolder.tvPositionItemCount.text = positionItem.count.toString()

        viewHolder.view.setOnLongClickListener {
            onPositionItemLongClickListener?.invoke(positionItem)
            true
        }
        viewHolder.view.setOnClickListener {
            onPositionItemClickListener?.invoke(positionItem)
        }
        //viewHolder.view.context
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).enabled) {
            NOT_PURCHASED_TYPE
        } else {
            PURCHASED_TYPE
        }
    }

    companion object {
        const val NOT_PURCHASED_TYPE = 100
        const val PURCHASED_TYPE = 101
    }
}
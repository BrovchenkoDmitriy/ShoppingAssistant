package com.example.shoppingassistant.presentation.shoppingListRecycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.shoppingassistant.databinding.PositionItemBinding
import com.example.shoppingassistant.domain.PositionItem

class PositionListAdapter :
    ListAdapter<PositionItem, PositionItemViewHolder>(PositionItemDiffCallBack()) {

    var onPositionItemLongClickListener: ((PositionItem) -> Unit)? = null
    var onPositionItemClickListener: ((PositionItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PositionItemViewHolder {
        val binding = PositionItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PositionItemViewHolder(binding)
    }

    //привязываем к вьюшкам информацию
    override fun onBindViewHolder(viewHolder: PositionItemViewHolder, position: Int) {
        val positionItem = getItem(position)
        val binding = viewHolder.binding

        binding.tvPositionItemName.text = positionItem.name
        binding.tvPositionItemCount.text = positionItem.count.toString()
        if (!positionItem.enabled) {
            binding.tvPurchased.visibility = View.VISIBLE
        } else binding.tvPurchased.visibility = View.GONE

        binding.root.setOnLongClickListener {
            onPositionItemLongClickListener?.invoke(positionItem)
            true
        }
        binding.root.setOnClickListener {
            onPositionItemClickListener?.invoke(positionItem)
        }
    }
}
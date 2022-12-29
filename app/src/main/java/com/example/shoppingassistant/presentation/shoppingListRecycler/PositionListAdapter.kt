package com.example.shoppingassistant.presentation.shoppingListRecycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import com.example.shoppingassistant.R
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
            changeStatusToPurchased(binding.tvPositionItemName, viewHolder.itemView.context)
            changeStatusToPurchased(binding.tvPositionItemCount, viewHolder.itemView.context)
            binding.itemFrame.strokeColor = ContextCompat.getColor(viewHolder.itemView.context, R.color.dark_grey)

        } else {
            binding.tvPurchased.visibility = View.GONE
            changeStatusToNotPurchased(binding.tvPositionItemName, viewHolder.itemView.context)
            changeStatusToNotPurchased(binding.tvPositionItemCount, viewHolder.itemView.context)
            binding.itemFrame.strokeColor = ContextCompat.getColor(viewHolder.itemView.context, R.color.black)
        }

        binding.root.setOnLongClickListener {
            onPositionItemLongClickListener?.invoke(positionItem)
            true
        }
        binding.root.setOnClickListener {
            onPositionItemClickListener?.invoke(positionItem)
        }
        viewHolder.itemView.context
    }

    private fun changeStatusToPurchased(textView: TextView, context: Context){
        textView.setTextColor(ContextCompat.getColor(context, R.color.dark_grey))
    }
    private fun changeStatusToNotPurchased(textView: TextView, context: Context){
        textView.setTextColor(ContextCompat.getColor(context, R.color.black))
    }
}
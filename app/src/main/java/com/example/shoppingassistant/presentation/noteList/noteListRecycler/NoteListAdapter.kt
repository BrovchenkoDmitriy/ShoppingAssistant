package com.example.shoppingassistant.presentation.noteList.noteListRecycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.shoppingassistant.databinding.NoteItemBinding
import com.example.shoppingassistant.domain.ShopItem

class NoteListAdapter :
    ListAdapter<ShopItem, NoteItemViewHolder>(NoteItemDiffCallBack()) {

    var onNoteItemLocationButtonClickListener: ((ShopItem) -> Unit)? = null
    var onNoteItemPhotoClickListener: ((ShopItem) -> Unit)? = null
    var onPositionItemClickListener: ((ShopItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteItemViewHolder {
        val binding = NoteItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NoteItemViewHolder(binding)
    }

    //привязываем к вьюшкам информацию
    override fun onBindViewHolder(viewHolder: NoteItemViewHolder, position: Int) {
        val noteItem = getItem(position)
        with(viewHolder.binding){
            tvNoteItemName.text = noteItem.name
            tvNoteItemPrice.text = noteItem.price.toString()
            tvNoteItemShop.text = noteItem.marketName
            tvNoteItemDate.text = noteItem.date.toString()

            ivPhoto.setOnClickListener {
                onNoteItemPhotoClickListener?.invoke(noteItem)
            }

            ibLocationButton.setOnClickListener {
                onNoteItemLocationButtonClickListener?.invoke(noteItem)
            }

            root.setOnClickListener {
                onPositionItemClickListener?.invoke(noteItem)
            }
        }
    }
}
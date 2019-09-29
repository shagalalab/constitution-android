package com.shagalalab.constitution.list.adapter

import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.shagalalab.constitution.list.model.Item
import kotlinx.android.synthetic.main.item_view.view.*

class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun populateModel(model: Item, itemClickListener: ItemClickListener) {
        itemView.title_text.text = model.title
        itemView.description_text.text = model.description
        itemView.description_text.isVisible = model.description.isNotEmpty()
        itemView.setOnClickListener {
            itemClickListener.onItemClick(model)
        }
    }
}

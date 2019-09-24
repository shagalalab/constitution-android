package com.shagalalab.constitution.list.adapter

import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.shagalalab.constitution.list.model.Item
import kotlinx.android.synthetic.main.item_view.view.*

class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var title: TextView = itemView.title_text
    var description: TextView = itemView.description_text

    fun populateModel(model: Item, itemClickListener: ItemClickListener) {
        title.text = model.title
        description.text = model.description
        description.isVisible = model.description.isNotEmpty()
        itemView.setOnClickListener {
            itemClickListener.onItemClick(model)
        }
    }
}

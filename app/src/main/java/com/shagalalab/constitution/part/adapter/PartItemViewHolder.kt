package com.shagalalab.constitution.part.adapter

import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.shagalalab.constitution.data.models.PartModel
import kotlinx.android.synthetic.main.item_view.view.*

class PartItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun populateModel(model: PartModel, itemClickListener: ItemClickListener) {
        itemView.title_text.text = model.title
        itemView.description_text.text = model.description
        itemView.description_text.isVisible = model.description.isNotEmpty()
        itemView.setOnClickListener {
            itemClickListener.onItemClick(model)
        }
    }
}
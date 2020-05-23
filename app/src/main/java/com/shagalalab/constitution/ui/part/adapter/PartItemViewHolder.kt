package com.shagalalab.constitution.ui.part.adapter

import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.shagalalab.constitution.data.models.PartModel
import kotlinx.android.synthetic.main.item_view.view.*

class PartItemViewHolder(itemView: View, private val itemClickListener: (itemId: Int) -> Unit) :
    RecyclerView.ViewHolder(itemView) {

    fun populateModel(model: PartModel) {
        itemView.apply {
            title_text.text = model.title
            description_text.text = model.description
            description_text.isVisible = model.description.isNotEmpty()
            setOnClickListener {
                itemClickListener.invoke(model.id)
            }
        }
    }
}

package com.shagalalab.constitution.chapter.adapter

import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.shagalalab.constitution.data.models.ChapterModel
import kotlinx.android.synthetic.main.item_view.view.*

class ChapterItemViewHolder(itemView: View, private val itemClick: (model: ChapterModel) -> Unit) :
    RecyclerView.ViewHolder(itemView) {

    fun populateModel(model: ChapterModel) {
        itemView.title_text.text = model.title
        itemView.description_text.text = model.description
        itemView.description_text.isVisible = model.description.isNotEmpty()
        itemView.setOnClickListener {
            itemClick.invoke(model)
        }
    }
}

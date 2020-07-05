package com.shagalalab.constitution.ui.search.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_view.view.*

class ArticleViewHolder(itemView: View, private val itemClick: (model: ArticleItem) -> Unit) :
    RecyclerView.ViewHolder(itemView) {
    fun populateModel(model: ArticleItem) {
        itemView.title_text.text = model.title
        itemView.description_text.text = model.description
        itemView.setOnClickListener {
            itemClick.invoke(model)
        }
    }
}

package com.shagalalab.constitution.search.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_view.view.*

class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun populateModel(model: ArticleItem) {
        itemView.title_text.text = model.title
        itemView.description_text.text = model.description
    }
}

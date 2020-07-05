package com.shagalalab.constitution.ui.search.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.lang_item_view.view.*

class LangViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun populateModel(model: LangItem) {
        itemView.tvLang.text = model.lang
    }
}

package com.shagalalab.constitution.list.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shagalalab.constitution.R
import com.shagalalab.constitution.list.model.Item

class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var title: TextView = itemView.findViewById(R.id.title_text)
    var description: TextView = itemView.findViewById(R.id.description_text)

    fun populateModel(model: Item, itemClickListener: ItemClickListener) {
        title.text = model.title
        description.text = model.description
        if (model.description == "") {
            description.visibility = View.GONE
        }
        itemView.setOnClickListener {
            itemClickListener.onItemClick(model)
        }
    }
}

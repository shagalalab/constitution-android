package com.shagalalab.constitution.ui.search.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.shagalalab.constitution.databinding.ItemViewBinding

class ArticleViewHolder(itemView: View, private val itemClick: (model: ArticleItem) -> Unit) :
    RecyclerView.ViewHolder(itemView) {
    private val binding by viewBinding(ItemViewBinding::bind)

    fun populateModel(model: ArticleItem) {
        binding.titleText.text = model.title
        binding.descriptionText.text = model.description
        binding.root.setOnClickListener {
            itemClick.invoke(model)
        }
    }
}

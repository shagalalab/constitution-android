package com.shagalalab.constitution.ui.chapter.adapter

import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.shagalalab.constitution.data.models.ChapterModel
import com.shagalalab.constitution.databinding.ItemViewBinding

class ChapterItemViewHolder(itemView: View, private val itemClick: (model: ChapterModel) -> Unit) :
    RecyclerView.ViewHolder(itemView) {
    private val binding by viewBinding(ItemViewBinding::bind)

    fun populateModel(model: ChapterModel) {
        binding.titleText.text = model.title
        binding.descriptionText.text = model.description
        binding.descriptionText.isVisible = model.description.isNotEmpty()
        binding.root.setOnClickListener {
            itemClick.invoke(model)
        }
    }
}

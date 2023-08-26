package com.shagalalab.constitution.ui.part.adapter

import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.shagalalab.constitution.data.models.PartModel
import com.shagalalab.constitution.databinding.ItemViewBinding

class PartItemViewHolder(itemView: View, private val itemClickListener: (itemId: Int) -> Unit) :
    RecyclerView.ViewHolder(itemView) {
    private val binding by viewBinding(ItemViewBinding::bind)

    fun populateModel(model: PartModel) {
        itemView.apply {
            binding.titleText.text = model.title
            binding.descriptionText.text = model.description
            binding.descriptionText.isVisible = model.description.isNotEmpty()
            setOnClickListener {
                itemClickListener.invoke(model.id)
            }
        }
    }
}

package com.shagalalab.constitution.ui.search.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.shagalalab.constitution.databinding.LangItemViewBinding

class LangViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding by viewBinding(LangItemViewBinding::bind)

    fun populateModel(model: LangItem) {
        binding.tvLang.text = model.lang
    }
}

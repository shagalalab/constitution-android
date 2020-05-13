package com.shagalalab.constitution.chapter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shagalalab.constitution.R
import com.shagalalab.constitution.data.models.ChapterModel

class ChapterAdapter :
    RecyclerView.Adapter<ChapterItemViewHolder>() {

    private var models: List<ChapterModel> = arrayListOf()

    private lateinit var itemClick: (ChapterModel) -> Unit

    fun setItemClick(itemCLick: (model: ChapterModel) -> Unit) {
        this.itemClick = itemCLick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapterItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ChapterItemViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ChapterItemViewHolder, position: Int) {
        holder.populateModel(models[position])
    }

    override fun getItemCount(): Int {
        return models.size
    }

    fun setData(models: List<ChapterModel>) {
        this.models = models
        notifyDataSetChanged()
    }
}

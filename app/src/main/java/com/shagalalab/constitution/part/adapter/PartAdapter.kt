package com.shagalalab.constitution.part.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shagalalab.constitution.R
import com.shagalalab.constitution.data.models.PartModel

class PartAdapter :
    RecyclerView.Adapter<PartItemViewHolder>() {

    private var models: List<PartModel> = arrayListOf()

    fun setData(models: List<PartModel>) {
        this.models = models
        notifyDataSetChanged()
    }

    private var onItemClick = { _: Int ->

    }

    fun setOnClick(modelId: (Int) -> Unit) {
        this.onItemClick = modelId
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return PartItemViewHolder(view, onItemClick)
    }

    override fun onBindViewHolder(holder: PartItemViewHolder, position: Int) {
        holder.populateModel(models[position])
    }

    override fun getItemCount(): Int {
        return models.size
    }
}

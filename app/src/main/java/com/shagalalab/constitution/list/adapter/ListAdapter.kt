package com.shagalalab.constitution.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shagalalab.constitution.R
import com.shagalalab.constitution.data.models.PartModel

class ListAdapter(private val itemClickListener: ItemClickListener) :
    RecyclerView.Adapter<ItemViewHolder>() {

    private var models: List<PartModel> = arrayListOf()

    fun setData(models: List<PartModel>) {
        this.models = models
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return models.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.populateModel(models[position], itemClickListener)
    }
}

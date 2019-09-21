package com.shagalalab.constitution.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shagalalab.constitution.R
import com.shagalalab.constitution.list.model.Item

class ListAdapter(private val itemClickListener: ItemClickListener) : RecyclerView.Adapter<ItemViewHolder>() {

    var models: MutableList<Item> = arrayListOf()

    fun setData(models: MutableList<Item>) {
        this.models = models
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_list_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return models.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.populateModel(models[position], itemClickListener)
    }
}

package com.shagalalab.constitution.part.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shagalalab.constitution.R
import com.shagalalab.constitution.data.models.PartModel

class PartAdapter(private val itemClickListener: ItemClickListener) :
    RecyclerView.Adapter<PartItemViewHolder>() {

    private var models: List<PartModel> = arrayListOf()

    fun setData(models: List<PartModel>) {
        this.models = models
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return PartItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return models.size
    }

    override fun onBindViewHolder(holder: PartItemViewHolder, position: Int) {
        holder.populateModel(models[position], itemClickListener)
    }
}

package com.shagalalab.constitution.article.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shagalalab.constitution.R
import com.shagalalab.constitution.data.models.ArticleModel

class ArticleAdapter : RecyclerView.Adapter<ArticleItemViewHolder>() {

    private var models: List<ArticleModel> = arrayListOf()

    fun setData(models: List<ArticleModel>) {
        this.models = models
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ArticleItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return models.size
    }

    override fun onBindViewHolder(holder: ArticleItemViewHolder, position: Int) {
        holder.populateModel(models[position])
    }
}

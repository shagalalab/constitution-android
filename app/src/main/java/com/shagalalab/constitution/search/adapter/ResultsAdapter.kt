package com.shagalalab.constitution.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shagalalab.constitution.R

class ResultsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var models: List<ListItem> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemViewType(position: Int): Int {
        return models[position].type
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == ListItem.ARTICLE_TYPE) {
            (holder as LangViewHolder).populateModel(models[position] as LangItem)
        } else {
            (holder as ArticleViewHolder).populateModel(models[position] as ArticleItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        return if (viewType == ListItem.ARTICLE_TYPE) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
            ArticleViewHolder(view)
        } else {
            view =
                LayoutInflater.from(parent.context).inflate(R.layout.lang_item_view, parent, false)
            LangViewHolder(view)
        }
    }

    override fun getItemCount(): Int = models.size


}

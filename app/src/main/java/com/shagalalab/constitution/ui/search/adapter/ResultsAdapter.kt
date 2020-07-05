package com.shagalalab.constitution.ui.search.adapter

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
            (holder as ArticleViewHolder).populateModel(models[position] as ArticleItem)
        } else {
            (holder as LangViewHolder).populateModel(models[position] as LangItem)
        }
    }

    private lateinit var itemClick: (ArticleItem) -> Unit

    fun setItemClick(itemCLick: (model: ArticleItem) -> Unit) {
        this.itemClick = itemCLick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        return if (viewType == ListItem.ARTICLE_TYPE) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
            ArticleViewHolder(view, itemClick)
        } else {
            view =
                LayoutInflater.from(parent.context).inflate(R.layout.lang_item_view, parent, false)
            LangViewHolder(view)
        }
    }

    override fun getItemCount(): Int = models.size
}

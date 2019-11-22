package com.shagalalab.constitution.article.adapter

import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.StyleSpan
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shagalalab.constitution.data.models.ArticleModel
import kotlinx.android.synthetic.main.item_view.view.*

class ArticleItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun populateModel(model: ArticleModel) {
        val s = SpannableStringBuilder()
        val st = StyleSpan(android.graphics.Typeface.BOLD)
        s.append(model.title, st, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        s.append(". ")
        s.append(model.description)
        itemView.title_text.setText(s, TextView.BufferType.NORMAL)
        itemView.description_text.visibility = View.GONE
    }
}

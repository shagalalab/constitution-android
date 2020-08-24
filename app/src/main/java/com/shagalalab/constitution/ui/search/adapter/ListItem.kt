package com.shagalalab.constitution.ui.search.adapter

import android.text.SpannableStringBuilder

open class ListItem(var type: Int) {
    companion object {
        const val LANG_TYPE = 1
        const val ARTICLE_TYPE = 2
    }
}

class ArticleItem(val id: Int, val title: String, val description: SpannableStringBuilder) :
    ListItem(ARTICLE_TYPE)

class LangItem(var lang: String) : ListItem(LANG_TYPE)

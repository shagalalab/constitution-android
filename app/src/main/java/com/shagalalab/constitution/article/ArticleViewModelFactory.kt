package com.shagalalab.constitution.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shagalalab.constitution.data.dao.ArticleDao

class ArticleViewModelFactory(private val articleDao: ArticleDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArticleViewModel::class.java)) {
            return ArticleViewModel(articleDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

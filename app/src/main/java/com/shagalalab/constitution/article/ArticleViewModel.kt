package com.shagalalab.constitution.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shagalalab.constitution.data.dao.ArticleDao
import com.shagalalab.constitution.data.models.ArticleModel
import java.util.concurrent.Executors

class ArticleViewModel(private val articleDao: ArticleDao) : ViewModel() {

    private val articleListLiveData: MutableLiveData<List<ArticleModel>> = MutableLiveData()
    val articleList: LiveData<List<ArticleModel>> = articleListLiveData

    fun getArticles(chapterId: Int) {
        Executors.newSingleThreadExecutor().execute {
            articleListLiveData.postValue(articleDao.getArticlesByChapterId(chapterId))
        }
    }

    fun getArticlesByPartId(chapterId: Int) {
        Executors.newSingleThreadExecutor().execute {
            articleListLiveData.postValue(articleDao.getArticlesByPartId(chapterId))
        }
    }
}

package com.shagalalab.constitution.ui.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shagalalab.constitution.data.dao.ArticleDao
import com.shagalalab.constitution.data.models.ArticleModel
import java.util.concurrent.Executors

class ArticleViewModel(private val articleDao: ArticleDao) : ViewModel() {

    private val articleListLiveData: MutableLiveData<List<ArticleModel>> = MutableLiveData()
    val articleList: LiveData<List<ArticleModel>> = articleListLiveData
    private val articleLivaData: MutableLiveData<ArticleModel> = MutableLiveData()
    val article: LiveData<ArticleModel> = articleLivaData

    fun getArticlesByChapterId(chapterId: Int) {
        Executors.newSingleThreadExecutor().execute {
            articleListLiveData.postValue(articleDao.getArticlesByChapterId(chapterId))
        }
    }

    fun getAllArticles() {
        Executors.newSingleThreadExecutor().execute {
            articleListLiveData.postValue(articleDao.getArticles())
        }
    }

    fun getArticlesByPartId(partId: Int) {
        Executors.newSingleThreadExecutor().execute {
            articleListLiveData.postValue(articleDao.getArticlesByPartId(partId))
        }
    }

    fun findArticlesByWord(word: String) {
        Executors.newSingleThreadExecutor().execute {
            articleListLiveData.postValue(articleDao.findArticleByWord("%$word%"))
        }
    }

    fun getArticleById(id: Int) {
        Executors.newSingleThreadExecutor().execute {
            articleLivaData.postValue(articleDao.getArticleById(id))
        }
    }
}

package com.shagalalab.constitution.article

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
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

    fun normalizedDescription(it: List<ArticleModel>): SpannableStringBuilder {
        val spannedString = SpannableStringBuilder()
        it.forEach {
            spannedString.append(it.title)
            spannedString.setSpan(
                StyleSpan(Typeface.BOLD),
                spannedString.length - it.title.length,
                spannedString.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            spannedString.append(". ")
            spannedString.append(it.description.replace("\\n", "\n"))
            spannedString.append("\n")
        }
        return spannedString
    }
}

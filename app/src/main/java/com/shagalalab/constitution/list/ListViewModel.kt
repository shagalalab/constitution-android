package com.shagalalab.constitution.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shagalalab.constitution.data.dao.ArticleDao
import com.shagalalab.constitution.data.dao.ChapterDao
import com.shagalalab.constitution.data.dao.PartDao
import com.shagalalab.constitution.data.models.PartModel
import java.util.concurrent.Executors

class ListViewModel(
    private val partDao: PartDao,
    private val chapterDao: ChapterDao,
    private val articleDao: ArticleDao
) : ViewModel() {
    var partList: MutableLiveData<MutableList<PartModel>> = MutableLiveData()

    fun getPartsByLangId(langId: Int) {
        Executors.newSingleThreadExecutor().execute {
            partList.postValue(partDao.getPartListByLangId(langId))
        }
    }
}

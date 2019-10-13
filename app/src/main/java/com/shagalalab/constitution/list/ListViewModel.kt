package com.shagalalab.constitution.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shagalalab.constitution.data.dao.ArticleDao
import com.shagalalab.constitution.data.dao.ChapterDao
import com.shagalalab.constitution.data.dao.PartDao
import com.shagalalab.constitution.data.models.PartModel

class ListViewModel(
    private val partDao: PartDao,
    private val chapterDao: ChapterDao,
    private val articleDao: ArticleDao
) : ViewModel() {
    var partList: MutableLiveData<List<PartModel>> = MutableLiveData()
    var result: List<PartModel> = arrayListOf()
    fun getPartsByLangId(langId: Int) {
        partList.value = partDao.getPartListByLangId(langId)
        /*Executors.newSingleThreadExecutor().execute {
            result = partDao.getPartListByLangId(langId)
        }
        partList.value = result*/
    }
}

package com.shagalalab.constitution.chapter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shagalalab.constitution.data.dao.ChapterDao
import com.shagalalab.constitution.data.models.ChapterModel
import java.util.concurrent.Executors

class ChapterViewModel(private val chapterDao: ChapterDao) : ViewModel() {

    private var chapterList: MutableLiveData<List<ChapterModel>> = MutableLiveData()
    var chapterListToObserveOutside: MutableLiveData<List<ChapterModel>> = MutableLiveData()
    fun getChaptersByPartId(partId: Int) {
        Executors.newSingleThreadExecutor().execute {
            chapterList.postValue(chapterDao.getChaptersByPartId(partId))
        }
        chapterListToObserveOutside = chapterList
    }
}

package com.shagalalab.constitution.chapter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shagalalab.constitution.data.dao.ChapterDao
import com.shagalalab.constitution.data.models.ChapterModel
import java.util.concurrent.Executors

class ChapterViewModel(private val chapterDao: ChapterDao) : ViewModel() {

    private val chapterListLiveData: MutableLiveData<List<ChapterModel>> = MutableLiveData()
    lateinit var chapterList: LiveData<List<ChapterModel>>
    fun getChaptersByPartId(partId: Int) {
        Executors.newSingleThreadExecutor().execute {
            chapterListLiveData.postValue(chapterDao.getChaptersByPartId(partId))
        }
        chapterList = chapterListLiveData
    }
}
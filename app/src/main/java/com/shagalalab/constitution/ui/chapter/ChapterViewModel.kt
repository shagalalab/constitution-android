package com.shagalalab.constitution.ui.chapter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shagalalab.constitution.data.dao.ChapterDao
import com.shagalalab.constitution.data.models.ChapterModel
import java.util.concurrent.Executor

class ChapterViewModel(
    private val chapterDao: ChapterDao,
    private val executor: Executor
) : ViewModel() {

    private val chapterListLiveData: MutableLiveData<List<ChapterModel>> = MutableLiveData()
    val chapterList: LiveData<List<ChapterModel>> = chapterListLiveData

    fun getChaptersByPartId(partId: Int) {
        executor.execute {
            chapterListLiveData.postValue(chapterDao.getChaptersByPartId(partId))
        }
    }

    fun getAllChapters() {
        executor.execute {
            chapterListLiveData.postValue(chapterDao.getChapters())
        }
    }
}

package com.shagalalab.constitution.chapter

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shagalalab.constitution.data.dao.ChapterDao
import com.shagalalab.constitution.data.models.ChapterModel
import java.util.concurrent.Executors

class ChapterViewModel(private val chapterDao: ChapterDao) : ViewModel() {

    private val chapterListLiveData: MutableLiveData<List<ChapterModel>> = MutableLiveData()
    val chapterList: LiveData<List<ChapterModel>> = chapterListLiveData

    init {
        Log.i("ChapterViewModel", "ChapterViewModel created!")
    }

    fun getChaptersByPartId(partId: Int) {
        Executors.newSingleThreadExecutor().execute {
            chapterListLiveData.postValue(chapterDao.getChaptersByPartId(partId))
        }
    }
}

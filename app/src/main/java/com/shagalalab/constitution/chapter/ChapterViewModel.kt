package com.shagalalab.constitution.chapter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shagalalab.constitution.data.dao.ChapterDao
import com.shagalalab.constitution.data.models.ChapterModel
import java.util.concurrent.Executors

class ChapterViewModel(private val chapterDao: ChapterDao) : ViewModel() {

    private val chapterListLiveData: MutableLiveData<List<ChapterModel>> = MutableLiveData()
    val chapterList: LiveData<List<ChapterModel>> = chapterListLiveData

    private val chapterViewModel: MutableLiveData<ChapterModel> = MutableLiveData()
    val chapter: LiveData<ChapterModel> = chapterViewModel

    fun getChaptersByPartId(partId: Int) {
        Executors.newSingleThreadExecutor().execute {
            chapterListLiveData.postValue(chapterDao.getChaptersByPartId(partId))
        }
    }

    fun getChapterById(id: Int) {
        Executors.newSingleThreadExecutor().execute {
            chapterViewModel.postValue(chapterDao.getChapterById(id))
        }
    }
}

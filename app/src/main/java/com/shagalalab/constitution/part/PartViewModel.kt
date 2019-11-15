package com.shagalalab.constitution.part

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shagalalab.constitution.data.dao.ChapterDao
import com.shagalalab.constitution.data.dao.PartDao
import com.shagalalab.constitution.data.models.PartModel
import java.util.concurrent.Executors

class PartViewModel(
    private val partDao: PartDao,
    private val chapterDao: ChapterDao
) : ViewModel() {
    private val partListLiveData: MutableLiveData<List<PartModel>> = MutableLiveData()
    val partList: LiveData<List<PartModel>> = partListLiveData
    private val chapterClickResultLiveData: MutableLiveData<Pair<Int, Boolean>> = MutableLiveData()
    val chapterClickResult: LiveData<Pair<Int, Boolean>> = chapterClickResultLiveData

    fun getPartsByLangId(langId: Int) {
        Executors.newSingleThreadExecutor().execute {
            partListLiveData.postValue(partDao.getPartsByLanguage(langId))
        }
    }

    fun getChapterScreen(id: Int) {
        Executors.newSingleThreadExecutor().execute {
            chapterClickResultLiveData.postValue(
                Pair(
                    id,
                    chapterDao.getChaptersByPartId(id).isNotEmpty()
                )
            )
        }
    }
}

package com.shagalalab.constitution.ui.part

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

    private val partLiveData: MutableLiveData<PartModel> = MutableLiveData()
    val part: LiveData<PartModel> = partLiveData

    private val chapterClickResultLiveData: MutableLiveData<Int> = MutableLiveData()
    val chapterClickResult: LiveData<Int> = chapterClickResultLiveData

    private val preambleClickResultLiveData: MutableLiveData<Int> = MutableLiveData()
    val preambleClickResult: LiveData<Int> = preambleClickResultLiveData

    fun getPartsByLangId(langId: Int) {
        Executors.newSingleThreadExecutor().execute {
            partListLiveData.postValue(partDao.getPartsByLanguage(langId))
        }
    }

    fun getPartById(id: Int) {
        Executors.newSingleThreadExecutor().execute {
            partLiveData.postValue(partDao.getPartsById(id))
        }
    }

    fun getChapterScreen(id: Int) {
        Executors.newSingleThreadExecutor().execute {
            if (chapterDao.getChaptersByPartId(id).isNotEmpty()) {
                chapterClickResultLiveData.postValue(id)
            } else {
                preambleClickResultLiveData.postValue(id)
            }
        }
    }

    fun getAllParts() {
        Executors.newSingleThreadExecutor().execute {
            partListLiveData.postValue(partDao.getParts())
        }
    }
}

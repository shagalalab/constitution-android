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
    var partList: LiveData<List<PartModel>> = partListLiveData
    var modelLiveData: MutableLiveData<Pair<Int, Boolean>> = MutableLiveData()

    fun getPartsByLangId(langId: Int) {
        Executors.newSingleThreadExecutor().execute {
            partListLiveData.postValue(partDao.getPartsByLanguage(langId))
        }
    }

    fun getChapterScreen(id: Int) {
        Executors.newSingleThreadExecutor().execute {
            modelLiveData.postValue(
                Pair(
                    id,
                    chapterDao.getChaptersByPartId(id).isNotEmpty()
                )
            )
        }
    }
}

package com.shagalalab.constitution.part

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shagalalab.constitution.data.dao.PartDao
import com.shagalalab.constitution.data.models.PartModel
import java.util.concurrent.Executors

class PartViewModel(
    private val partDao: PartDao
) : ViewModel() {
    var partList: MutableLiveData<List<PartModel>> = MutableLiveData()

    fun getPartsByLangId(langId: Int) {
        Executors.newSingleThreadExecutor().execute {
            partList.postValue(partDao.getPartsByLanguage(langId))
        }
    }
}

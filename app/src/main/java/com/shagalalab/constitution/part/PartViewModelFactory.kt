package com.shagalalab.constitution.part

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shagalalab.constitution.data.dao.ChapterDao
import com.shagalalab.constitution.data.dao.PartDao

class PartViewModelFactory(private val partDao: PartDao, private val chapterDao: ChapterDao) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PartViewModel::class.java)) {
            return PartViewModel(partDao, chapterDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}

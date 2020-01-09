package com.shagalalab.constitution.chapter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shagalalab.constitution.data.dao.ChapterDao

class ChapterViewModelFactory(private val chapterDao: ChapterDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ChapterViewModel::class.java)){
            return ChapterViewModel(chapterDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

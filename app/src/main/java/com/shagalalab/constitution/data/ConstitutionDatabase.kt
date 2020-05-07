package com.shagalalab.constitution.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shagalalab.constitution.data.dao.ArticleDao
import com.shagalalab.constitution.data.dao.ChapterDao
import com.shagalalab.constitution.data.dao.PartDao
import com.shagalalab.constitution.data.models.ArticleModel
import com.shagalalab.constitution.data.models.ChapterModel
import com.shagalalab.constitution.data.models.PartModel

@Database(
    entities = [ArticleModel::class, ChapterModel::class, PartModel::class],
    version = 1,
    exportSchema = false
)
abstract class ConstitutionDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao
    abstract fun chapterDao(): ChapterDao
    abstract fun partDao(): PartDao
}

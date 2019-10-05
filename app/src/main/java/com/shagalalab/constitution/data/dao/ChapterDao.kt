package com.shagalalab.constitution.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.shagalalab.constitution.data.models.ChapterModel

@Dao
interface ChapterDao {

    @Query("SELECT * FROM chapter")
    fun getChapters(): List<ChapterModel>
}

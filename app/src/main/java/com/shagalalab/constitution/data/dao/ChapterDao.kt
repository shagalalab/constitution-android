package com.shagalalab.constitution.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.shagalalab.constitution.data.models.ChapterModel

@Dao
interface ChapterDao {

    @Query("SELECT * FROM chapters")
    fun getChapters(): List<ChapterModel>

    @Query("SELECT * FROM chapters WHERE part_id=:partId")
    fun getChaptersByPartId(partId: Int): List<ChapterModel>

    @Query("SELECT * FROM chapters WHERE id=:id")
    fun getChapterById(id: Int): ChapterModel
}

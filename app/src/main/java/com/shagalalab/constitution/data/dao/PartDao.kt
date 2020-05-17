package com.shagalalab.constitution.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.shagalalab.constitution.data.models.PartModel

@Dao
interface PartDao {

    @Query("SELECT * FROM parts")
    fun getParts(): LiveData<List<PartModel>>

    @Query("SELECT * FROM parts WHERE lang_id=:langId")
    fun getPartsByLanguage(langId: Int): List<PartModel>

    @Query("SELECT * FROM parts WHERE id=:id")
    fun getPartsById(id: Int): PartModel
}

package com.shagalalab.constitution.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.shagalalab.constitution.data.models.PartModel

@Dao
interface PartDao {

    @Query("SELECT * FROM part")
    fun getParts(): List<PartModel>
}

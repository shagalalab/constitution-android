package com.shagalalab.constitution.data

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class ChapterModel(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "title")
    var title: String = "",

    @ColumnInfo(name = "description")
    var description: String = "",

    @ColumnInfo(name = "part_id")
    var partId: Int = 0
)

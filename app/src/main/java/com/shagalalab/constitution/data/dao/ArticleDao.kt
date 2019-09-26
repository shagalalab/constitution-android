package com.shagalalab.constitution.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.shagalalab.constitution.data.models.ArticleModel

@Dao
interface ArticleDao {

    @Query("SELECT * FROM Article")
    fun getArticles(): List<ArticleModel>
}

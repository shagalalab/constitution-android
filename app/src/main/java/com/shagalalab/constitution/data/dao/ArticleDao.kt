package com.shagalalab.constitution.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.shagalalab.constitution.data.models.ArticleModel

@Dao
interface ArticleDao {

    @Query("SELECT * FROM articles")
    fun getArticles(): List<ArticleModel>

    @Query("SELECT * FROM articles WHERE chapter_id=:chapterId")
    fun getArticlesByChapterId(chapterId: Int): List<ArticleModel>

    @Query("SELECT * FROM articles WHERE part_id=:partId")
    fun getArticlesByPartId(partId: Int): List<ArticleModel>

    @Query("SELECT * FROM articles WHERE description like :word")
    fun findArticleByWord(word: String): List<ArticleModel>

    @Query("SELECT * FROM articles WHERE id=:id")
    fun getArticleById(id: Int): ArticleModel
}

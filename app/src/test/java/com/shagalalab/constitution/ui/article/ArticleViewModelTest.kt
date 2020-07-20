package com.shagalalab.constitution.ui.article

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.shagalalab.constitution.TestExecutor
import com.shagalalab.constitution.data.dao.ArticleDao
import com.shagalalab.constitution.data.models.ArticleModel
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ArticleViewModelTest {

    private lateinit var viewModel: ArticleViewModel
    private val articleModel = ArticleModel(1)
    private val articleModels = listOf(articleModel)
    private val executor = TestExecutor()
    private val articleMockDao = mockk<ArticleDao>()

    @Before
    fun setUp() {
        viewModel = ArticleViewModel(articleMockDao, executor)
    }

    @get: Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun getArticlesByChapterId() {
        every { articleMockDao.getArticlesByChapterId(1) } returns articleModels
        viewModel.getArticlesByChapterId(1)
        val result = viewModel.articleList.value
        Assert.assertEquals(result, articleModels)
    }

    @Test
    fun getArticlesByPartId() {
        every { articleMockDao.getArticlesByPartId(1) } returns articleModels
        viewModel.getArticlesByPartId(1)
        val result = viewModel.articleList.value
        Assert.assertEquals(result, articleModels)
    }

    @Test
    fun findArticlesByWord() {
        val word = "republic"
        every { articleMockDao.findArticleByWord("%$word%") } returns articleModels
        viewModel.findArticlesByWord(word)
        val result = viewModel.articleList.value
        Assert.assertEquals(result, articleModels)
    }

    @Test
    fun getArticleById() {
        every { articleMockDao.getArticleById(1) } returns articleModel
        viewModel.getArticleById(1)
        val result = viewModel.article.value
        Assert.assertEquals(result, articleModel)
    }
}

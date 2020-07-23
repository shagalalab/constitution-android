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
    fun `verify getArticlesByChapterId returns correct results`() {
        // given
        every { articleMockDao.getArticlesByChapterId(1) } returns articleModels

        // when
        viewModel.getArticlesByChapterId(1)

        // then
        val result = viewModel.articleList.value
        Assert.assertEquals(result, articleModels)
    }

    @Test
    fun `verify getArticlesByPartId returns correct results`() {
        // given
        every { articleMockDao.getArticlesByPartId(1) } returns articleModels

        // when
        viewModel.getArticlesByPartId(1)

        // then
        val result = viewModel.articleList.value
        Assert.assertEquals(result, articleModels)
    }

    @Test
    fun `verify findArticlesByWord returns correct results`() {
        // given
        val word = "republic"
        every { articleMockDao.findArticleByWord("%$word%") } returns articleModels

        // when
        viewModel.findArticlesByWord(word)

        // then
        val result = viewModel.articleList.value
        Assert.assertEquals(result, articleModels)
    }

    @Test
    fun `verify getArticleById returns correct results`() {
        // given
        every { articleMockDao.getArticleById(1) } returns articleModel

        // when
        viewModel.getArticleById(1)

        // then
        val result = viewModel.article.value
        Assert.assertEquals(result, articleModel)
    }
}

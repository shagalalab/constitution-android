package com.shagalalab.constitution.ui.chapter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.shagalalab.constitution.TestExecutor
import com.shagalalab.constitution.data.dao.ChapterDao
import com.shagalalab.constitution.data.models.ChapterModel
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ChapterViewModelTest {

    private lateinit var viewModel: ChapterViewModel
    private val daoMock = mockk<ChapterDao>()
    private val model = ChapterModel(1)
    private val models = listOf(model)
    private val executor = TestExecutor()

    @get: Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = ChapterViewModel(daoMock, executor)
    }

    @Test
    fun `verify getChaptersByPartId returns correct results`() {
        every { daoMock.getChaptersByPartId(1) } returns models
        viewModel.getChaptersByPartId(1)
        val result = viewModel.chapterList.value
        Assert.assertEquals(models, result)
    }

    @Test
    fun `verify getAllChapters returns correct results`() {
        every { daoMock.getChapters() } returns models
        viewModel.getAllChapters()
        val result = viewModel.chapterList.value
        Assert.assertEquals(models, result)
    }
}

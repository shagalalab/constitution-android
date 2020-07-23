package com.shagalalab.constitution.ui.part

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.shagalalab.constitution.TestExecutor
import com.shagalalab.constitution.data.dao.ChapterDao
import com.shagalalab.constitution.data.dao.PartDao
import com.shagalalab.constitution.data.models.ChapterModel
import com.shagalalab.constitution.data.models.PartModel
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PartViewModelTest {

    private lateinit var viewModel: PartViewModel
    private val partModel = PartModel(1)
    private val partModels = listOf(partModel)
    private val chapterModel = ChapterModel(1)
    private val partDaoMock = mockk<PartDao>()
    private val chapterDaoMock = mockk<ChapterDao>()
    private val executor = TestExecutor()

    @get: Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = PartViewModel(partDaoMock, chapterDaoMock, executor)
    }

    @Test
    fun `verify getPartsByLangId returns results`() {
        // given
        every { partDaoMock.getPartsByLanguage(1) } returns partModels

        // when
        viewModel.getPartsByLangId(1)

        // then
        val result = viewModel.partList.value
        Assert.assertEquals(result, partModels)
    }

    @Test
    fun `verify getAllParts returns correct results`() {
        // given
        every { partDaoMock.getParts() } returns partModels

        // when
        viewModel.getAllParts()

        // then
        val result = viewModel.partList.value
        Assert.assertEquals(result, partModels)
    }

    @Test
    fun `verify getChapterScreen returns correct results`() {
        // given
        every { chapterDaoMock.getChaptersByPartId(1) } returns listOf(chapterModel)
        every { chapterDaoMock.getChaptersByPartId(0) } returns listOf()

        // when
        viewModel.getChapterScreen(1)
        viewModel.getChapterScreen(0)

        // then
        val chapterClicked = viewModel.chapterClickResult.value
        val preambleClicked = viewModel.preambleClickResult.value
        Assert.assertEquals(chapterClicked, 1)
        Assert.assertEquals(preambleClicked, 0)
    }
}

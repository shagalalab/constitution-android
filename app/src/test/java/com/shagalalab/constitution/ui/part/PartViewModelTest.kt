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
    fun getPartsByLangId() {
        every { partDaoMock.getPartsByLanguage(1) } returns partModels
        viewModel.getPartsByLangId(1)
        val result = viewModel.partList.value
        Assert.assertEquals(result, partModels)
    }

    @Test
    fun getAllParts() {
        every { partDaoMock.getParts() } returns partModels
        viewModel.getAllParts()
        val result = viewModel.partList.value
        Assert.assertEquals(result, partModels)
    }

    @Test
    fun getChapterScreen() {
        every { chapterDaoMock.getChaptersByPartId(1) } returns listOf(chapterModel)
        viewModel.getChapterScreen(1)
        val result = viewModel.chapterClickResult.value
        Assert.assertEquals(result, 1)
    }
}

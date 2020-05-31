package com.shagalalab.constitution.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.shagalalab.constitution.R
import com.shagalalab.constitution.data.DataHolder
import com.shagalalab.constitution.ui.chapter.ChapterViewModel
import com.shagalalab.constitution.ui.part.PartViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val partViewModel: PartViewModel by viewModel()
    private val chapterViewModel: ChapterViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController = findNavController(R.id.container)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setSupportActionBar(toolbar)
        toolbar.setupWithNavController(navController, appBarConfiguration)
        partViewModel.getAllParts()
        partViewModel.partList.observe(this, Observer { partList ->
            DataHolder.parts = partList
            chapterViewModel.getAllChapters()
        })
        chapterViewModel.chapterList.observe(this, Observer {
            DataHolder.chapters = it
        })
    }
}

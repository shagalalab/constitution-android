package com.shagalalab.constitution.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.shagalalab.constitution.R
import com.shagalalab.constitution.data.DataHolder
import com.shagalalab.constitution.ui.MainActivity
import com.shagalalab.constitution.ui.chapter.ChapterViewModel
import com.shagalalab.constitution.ui.part.PartViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {

    private val partViewModel: PartViewModel by viewModel()
    private val chapterViewModel: ChapterViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        partViewModel.getAllParts()
        partViewModel.partList.observe(this, Observer { partList ->
            DataHolder.parts = partList
            chapterViewModel.getAllChapters()
        })
        chapterViewModel.chapterList.observe(this, Observer {
            DataHolder.chapters = it
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        })
    }
}

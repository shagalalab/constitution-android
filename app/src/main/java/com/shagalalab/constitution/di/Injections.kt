package com.shagalalab.constitution.di

import androidx.room.Room
import com.shagalalab.constitution.data.ConstitutionDatabase
import com.shagalalab.constitution.ui.article.ArticleViewModel
import com.shagalalab.constitution.ui.chapter.ChapterViewModel
import com.shagalalab.constitution.ui.part.PartViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {
    single {
        Room.databaseBuilder(
            androidContext(), ConstitutionDatabase::class.java,
            "constitution.db"
        )
            .createFromAsset("constitution.db")
            .build()
    }
    single { get<ConstitutionDatabase>().articleDao() }
    single { get<ConstitutionDatabase>().chapterDao() }
    single { get<ConstitutionDatabase>().partDao() }
}

val viewModelModule = module {
    viewModel { PartViewModel(get(), get()) }
    viewModel { ChapterViewModel(get()) }
    viewModel { ArticleViewModel(get()) }
}

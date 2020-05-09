package com.shagalalab.constitution.chapter

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import com.shagalalab.constitution.R
import com.shagalalab.constitution.chapter.adapter.ChapterAdapter
import com.shagalalab.constitution.data.Language
import kotlinx.android.synthetic.main.fragment_chapter.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChapterFragment : Fragment(R.layout.fragment_chapter) {

    private val safeArgs: ChapterFragmentArgs by navArgs()
    private val lang by lazy { safeArgs.lang }
    private val partId by lazy { safeArgs.id }
    private val adapter = ChapterAdapter()

    private val viewModel: ChapterViewModel by viewModel()
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getChaptersByPartId(partId)
        viewModel.chapterList.observe(this, Observer {
            adapter.setData(it)
        })
        adapter.setItemClick {
            val action = ChapterFragmentDirections.actionChapterFragmentToArticleFragment(
                chooseTitleLang(lang),
                it.id,
                true
            )
            navController.navigate(action)

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        chapters_list.adapter = adapter
        chapters_list.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun chooseTitleLang(langCode: Int): String {
        return when (langCode) {
            Language.QQ.ordinal -> "Статьялар"
            Language.RU.ordinal -> "Статьи"
            Language.UZ.ordinal -> "Moddalar"
            Language.EN.ordinal -> "Articles"
            else -> ""
        }
    }
}

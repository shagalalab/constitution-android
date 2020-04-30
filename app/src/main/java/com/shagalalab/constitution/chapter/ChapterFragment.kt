package com.shagalalab.constitution.chapter

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import com.shagalalab.constitution.R
import com.shagalalab.constitution.chapter.adapter.ChapterAdapter
import com.shagalalab.constitution.chapter.adapter.ItemClickListener
import com.shagalalab.constitution.data.ConstitutionDatabase
import com.shagalalab.constitution.data.Language
import com.shagalalab.constitution.data.models.ChapterModel
import kotlinx.android.synthetic.main.fragment_chapter.*

class ChapterFragment : Fragment(R.layout.fragment_chapter), ItemClickListener {

    private val safeArgs: ChapterFragmentArgs by navArgs()
    private val lang by lazy { safeArgs.lang }
    private val partId by lazy { safeArgs.id }
    private val adapter = ChapterAdapter(this)
    private val viewModelFactory by lazy {
        ChapterViewModelFactory(ConstitutionDatabase.getInstance(requireContext()).chapterDao())
    }

    private lateinit var viewModel: ChapterViewModel
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ChapterViewModel::class.java)
        viewModel.getChaptersByPartId(partId)
        viewModel.chapterList.observe(this, Observer {
            adapter.setData(it)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        list_chapter.adapter = adapter
        list_chapter.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    override fun onItemClick(model: ChapterModel) {
        val action = ChapterFragmentDirections.actionChapterFragmentToArticleFragment(
            chooseTitleLang(lang),
            model.id,
            true
        )
        navController.navigate(action)
    }

    private fun chooseTitleLang(langCode: Int): String {
        return when (langCode) {
            Language.QQ.id -> "Статьялар"
            Language.RU.id -> "Статьи"
            Language.UZ.id -> "Moddalar"
            Language.EN.id -> "Articles"
            else -> ""
        }
    }
}

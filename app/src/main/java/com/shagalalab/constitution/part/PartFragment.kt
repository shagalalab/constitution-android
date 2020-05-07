package com.shagalalab.constitution.part

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import com.shagalalab.constitution.R
import com.shagalalab.constitution.data.Language
import com.shagalalab.constitution.part.adapter.ItemClickListener
import com.shagalalab.constitution.part.adapter.PartAdapter
import kotlinx.android.synthetic.main.fragment_part.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PartFragment : Fragment(R.layout.fragment_part), ItemClickListener {

    private val safeArgs: PartFragmentArgs by navArgs()
    private val lang by lazy { safeArgs.lang }
    private val adapter = PartAdapter(this)

    private lateinit var navController: NavController
    private val viewModel: PartViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.apply {
            getPartsByLangId(lang + 1)
            chapterClickResult.observe(this@PartFragment, Observer {
                changeToChapterFragment(it)
            })
            preambleClickResult.observe(this@PartFragment, Observer {
                changeToArticleFragment(it)
            })
            partList.observe(this@PartFragment, Observer {
                adapter.setData(it)
            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        parts_list.adapter = adapter
        parts_list.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }

    override fun onItemClick(id: Int) {
        viewModel.getChapterScreen(id)
    }

    private fun changeToChapterFragment(id: Int) {
        val action = PartFragmentDirections.actionPartFragmentToChapterFragment(
            chooseTitleLang(lang),
            id,
            lang
        )
        navController.navigate(action)
    }

    private fun changeToArticleFragment(id: Int) {
        val action = PartFragmentDirections.actionPartFragmentToArticleFragment(
            chooseArticleTitleLang(lang),
            id,
            false
        )
        navController.navigate(action)
    }

    private fun chooseArticleTitleLang(langCode: Int): String {
        return when (langCode) {
            Language.QQ.ordinal -> "Статьялар"
            Language.RU.ordinal -> "Статьи"
            Language.UZ.ordinal -> "Moddalar"
            Language.EN.ordinal -> "Articles"
            else -> ""
        }
    }

    private fun chooseTitleLang(langCode: Int): String {
        return when (langCode) {
            Language.QQ.ordinal -> "Баплар"
            Language.RU.ordinal -> "Главы"
            Language.UZ.ordinal -> "Boblar"
            Language.EN.ordinal -> "Chapters"
            else -> ""
        }
    }
}

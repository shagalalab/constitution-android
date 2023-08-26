package com.shagalalab.constitution.ui.chapter

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import by.kirich1409.viewbindingdelegate.viewBinding
import com.shagalalab.constitution.R
import com.shagalalab.constitution.data.Language
import com.shagalalab.constitution.databinding.FragmentChapterBinding
import com.shagalalab.constitution.ui.base.SearchableFragment
import com.shagalalab.constitution.ui.chapter.adapter.ChapterAdapter
import com.shagalalab.constitution.ui.main.MainFragmentDirections
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChapterFragment : SearchableFragment(R.layout.fragment_chapter) {
    private val binding by viewBinding(FragmentChapterBinding::bind)

    private val safeArgs: ChapterFragmentArgs by navArgs()
    private val lang by lazy { safeArgs.lang }
    private val partId by lazy { safeArgs.id }
    private val adapter = ChapterAdapter {
        val action = ChapterFragmentDirections.actionChapterFragmentToArticleFragment(
            chooseTitleLang(lang),
            it.id,
            true
        )
        navController.navigate(action)
    }

    private val viewModel: ChapterViewModel by viewModel()
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getChaptersByPartId(partId)
        viewModel.chapterList.observe(this) {
            adapter.setData(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        binding.chaptersList.adapter = adapter
        binding.chaptersList.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )

        searchText.onEach { query ->
            if (query.isNotEmpty()) {
                val action =
                    ChapterFragmentDirections.actionChapterFragmentToSearchResultFragment(
                        title = chooseSearchResultLang(lang),
                        query = query,
                        lang = lang
                    )
                navController.navigate(action)
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
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

    private fun chooseSearchResultLang(langCode: Int): String {
        return when (langCode) {
            Language.QQ.ordinal -> "Izlew nátiyjeleri"
            Language.RU.ordinal -> "Результаты запроса"
            Language.UZ.ordinal -> "Izlash natijalari"
            Language.EN.ordinal -> "Search Results"
            else -> ""
        }
    }
}

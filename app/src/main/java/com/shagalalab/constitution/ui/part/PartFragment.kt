package com.shagalalab.constitution.ui.part

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import by.kirich1409.viewbindingdelegate.viewBinding
import com.shagalalab.constitution.R
import com.shagalalab.constitution.data.Language
import com.shagalalab.constitution.databinding.FragmentPartBinding
import com.shagalalab.constitution.ui.base.SearchableFragment
import com.shagalalab.constitution.ui.part.adapter.PartAdapter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class PartFragment : SearchableFragment(R.layout.fragment_part) {
    private val binding by viewBinding(FragmentPartBinding::bind)

    private val safeArgs: PartFragmentArgs by navArgs()
    private val lang by lazy { safeArgs.lang }
    private val adapter = PartAdapter {
        viewModel.getChapterScreen(it)
    }

    private lateinit var navController: NavController
    private val viewModel: PartViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.apply {
            getPartsByLangId(lang + 1)
            chapterClickResult.observe(this@PartFragment) {
                changeToChapterFragment(it)
            }
            preambleClickResult.observe(this@PartFragment) {
                changeToArticleFragment(it)
            }
            partList.observe(this@PartFragment) {
                adapter.setData(it)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        binding.partsList.adapter = adapter
        binding.partsList.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        searchText.onEach { query ->
            if (query.isNotEmpty()) {
                val action = PartFragmentDirections.actionPartFragmentToSearchResultFragment(
                    title = chooseSearchResultLang(lang),
                    query = query,
                    lang = lang
                )
                navController.navigate(action)
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
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

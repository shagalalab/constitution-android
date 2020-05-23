package com.shagalalab.constitution.ui.part

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import com.shagalalab.constitution.R
import com.shagalalab.constitution.data.Language
import com.shagalalab.constitution.ui.part.adapter.PartAdapter
import kotlinx.android.synthetic.main.fragment_part.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PartFragment : Fragment(R.layout.fragment_part) {

    private val safeArgs: PartFragmentArgs by navArgs()
    private val lang by lazy { safeArgs.lang }
    private val adapter = PartAdapter()

    private lateinit var navController: NavController
    private val viewModel: PartViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

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

        adapter.setOnClick {
            viewModel.getChapterScreen(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        val searchItem: MenuItem = menu.findItem(R.id.menu)
        val searchView: SearchView =
            MenuItemCompat.getActionView(searchItem) as SearchView
        searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                val action = PartFragmentDirections.actionPartFragmentToSearchResultFragment(query)
                navController.navigate(action)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                val action =
                    PartFragmentDirections.actionPartFragmentToSearchResultFragment(newText)
                // navController.navigate(action)
                return false
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        parts_list.adapter = adapter
        parts_list.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
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

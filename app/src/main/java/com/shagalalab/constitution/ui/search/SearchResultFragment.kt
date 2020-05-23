package com.shagalalab.constitution.ui.search

import android.os.Bundle
import android.text.SpannableStringBuilder
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
import com.shagalalab.constitution.data.DataHolder
import com.shagalalab.constitution.data.Language
import com.shagalalab.constitution.data.models.ArticleModel
import com.shagalalab.constitution.ui.article.ArticleViewModel
import com.shagalalab.constitution.ui.search.adapter.ArticleItem
import com.shagalalab.constitution.ui.search.adapter.LangItem
import com.shagalalab.constitution.ui.search.adapter.ListItem
import com.shagalalab.constitution.ui.search.adapter.ResultsAdapter
import kotlinx.android.synthetic.main.fragment_search_result.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchResultFragment : Fragment(R.layout.fragment_search_result) {

    private var adapter: ResultsAdapter = ResultsAdapter()
    private val safeArgs: SearchResultFragmentArgs by navArgs()
    private val viewModel: ArticleViewModel by viewModel()
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvResults.text = "Results for \"${safeArgs.query}\""
        navController = Navigation.findNavController(view)
        val query = safeArgs.query
        resultsList.adapter = adapter
        resultsList.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        adapter.setItemClick {
            val action = SearchResultFragmentDirections.actionSearchResultFragmentToFoundArticle(
                it.title,
                it.id,
                query
            )
            navController.navigate(action)
        }
        viewModel.findArticlesByWord(query)
        viewModel.articleList.observe(viewLifecycleOwner, Observer {
            setData(it)
        })
    }

    private val models: MutableList<ListItem> = mutableListOf()
    private var qq = false
    private var ru = false
    private var uz = false
    private var en = false

    private fun setData(data: List<ArticleModel>) {
        data.forEach {
            val langItem = LangItem(1, "")
            if (it.langId == Language.QQ.ordinal + 1 && !qq) {
                langItem.lang = "Qaraqalpaqsha"
                qq = true
                models.add(langItem)
            }
            if (it.langId == Language.RU.ordinal + 1 && !ru) {
                langItem.lang = "Русский"
                ru = true
                models.add(langItem)
            }
            if (it.langId == Language.UZ.ordinal + 1 && !uz) {
                langItem.lang = "O'zbekcha"
                uz = true
                models.add(langItem)
            }
            if (it.langId == Language.EN.ordinal + 1 && !en) {
                langItem.lang = "English"
                en = true
                models.add(langItem)
            }
            val spannedString = SpannableStringBuilder()
            spannedString.append(DataHolder.parts[it.partId - 1].title + ">>")
            if (it.chapterId != 0) spannedString.append(DataHolder.chapters[it.chapterId].title + ">>")
            spannedString.append(it.title)
            val articleItem =
                ArticleItem(2, spannedString.toString(), it.foundArticle(safeArgs.query), it.id)
            models.add(articleItem)
        }
        adapter.models = models
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        val searchItem: MenuItem = menu.findItem(R.id.menu)
        val searchView: SearchView =
            MenuItemCompat.getActionView(searchItem) as SearchView
        searchView.setQuery(safeArgs.query, true)
        val id = searchView.context.resources.getIdentifier(
            "androidx.appcompat.R.id.search_src_text",
            null,
            null
        )

        searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
//                val action = PartFragmentDirections.actionPartFragmentToSearchResultFragment(query)
//                navController.navigate(action)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                //        viewModel.findArticlesByWord(newText)
                return false
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }
}

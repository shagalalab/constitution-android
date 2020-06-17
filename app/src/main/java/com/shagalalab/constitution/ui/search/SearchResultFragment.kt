package com.shagalalab.constitution.ui.search

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
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
    private val models: MutableList<ListItem> = mutableListOf()
    private var currentLanguageId = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvResults.text = getString(R.string.results_for, safeArgs.query)
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

    private fun setData(data: List<ArticleModel>) {
        data.forEach {
            val langItem = LangItem(1, "")
            if (currentLanguageId == 0 || currentLanguageId != it.langId) {
                currentLanguageId = it.langId
                when (it.langId) {
                    Language.QQ.ordinal + 1 -> langItem.lang = "Qaraqalpaqsha"
                    Language.RU.ordinal + 1 -> langItem.lang = "Русский"
                    Language.UZ.ordinal + 1 -> langItem.lang = "O'zbekcha"
                    Language.EN.ordinal + 1 -> langItem.lang = "English"
                }
                models.add(langItem)
            }
            val spannedString = SpannableStringBuilder()
            spannedString.append(DataHolder.parts[it.partId - 1].title + "»")
            if (it.chapterId != 0) spannedString.append(DataHolder.chapters[it.chapterId].title + "»")
            spannedString.append(it.title)
            val articleItem =
                ArticleItem(2, spannedString.toString(), it.foundArticle(safeArgs.query), it.id)
            models.add(articleItem)
        }
        adapter.models = models
    }
}

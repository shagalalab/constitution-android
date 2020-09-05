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

        tvResults.text = when (safeArgs.lang) {
            Language.QQ.ordinal -> getString(R.string.qq_results_for, safeArgs.query)
            Language.RU.ordinal -> getString(R.string.ru_results_for, safeArgs.query)
            Language.UZ.ordinal -> getString(R.string.uz_results_for, safeArgs.query)
            Language.EN.ordinal -> getString(R.string.en_results_for, safeArgs.query)
            else -> getString(R.string.qq_results_for, safeArgs.query)
        }
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
        data.forEach { article ->
            val langItem = LangItem("")
            if (currentLanguageId == 0 || currentLanguageId != article.langId) {
                currentLanguageId = article.langId
                langItem.lang = when (article.langId - 1) {
                    Language.QQ.ordinal -> "Qaraqalpaqsha"
                    Language.RU.ordinal -> "Русский"
                    Language.UZ.ordinal -> "O'zbekcha"
                    Language.EN.ordinal -> "English"
                    else -> ""
                }
                models.add(langItem)
            }
            val spannedString = SpannableStringBuilder()
            spannedString.append(DataHolder.parts[article.partId - 1].title + " » ")
            if (article.chapterId != 0) spannedString.append(DataHolder.chapters[article.chapterId - 1].title + " » ")
            spannedString.append(article.title)
            val articleItem =
                ArticleItem(
                    article.id,
                    spannedString.toString(),
                    article.getShortenedDescription(safeArgs.query)
                )
            models.add(articleItem)
        }
        adapter.models = models
    }
}

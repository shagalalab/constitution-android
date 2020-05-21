package com.shagalalab.constitution.search

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
import com.shagalalab.constitution.article.ArticleViewModel
import com.shagalalab.constitution.chapter.ChapterViewModel
import com.shagalalab.constitution.data.Language
import com.shagalalab.constitution.data.models.ArticleModel
import com.shagalalab.constitution.part.PartViewModel
import com.shagalalab.constitution.search.adapter.ArticleItem
import com.shagalalab.constitution.search.adapter.LangItem
import com.shagalalab.constitution.search.adapter.ListItem
import com.shagalalab.constitution.search.adapter.ResultsAdapter
import kotlinx.android.synthetic.main.fragment_search_result.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchResultFragment : Fragment(R.layout.fragment_search_result) {

    private var adapter: ResultsAdapter = ResultsAdapter()
    private val safeArgs: SearchResultFragmentArgs by navArgs()
    private val viewModel: ArticleViewModel by viewModel()
    private val partViewModel: PartViewModel by viewModel()
    private val chapterViewModel: ChapterViewModel by viewModel()
    private lateinit var navController: NavController
    private var partTitle = ""
    private var chapterTitle = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
            val action = SearchResultFragmentDirections.actionSearchResultFragmentToArticleFragment(
                "Articles",
                it.id,
                true
            )
            navController.navigate(action)
        }
        viewModel.findArticlesByWord(query)
        viewModel.articleList.observe(viewLifecycleOwner, Observer {
            setData(it)
        })
        partViewModel.part.observe(viewLifecycleOwner, Observer {
            partTitle = it.title
        })
        chapterViewModel.chapter.observe(viewLifecycleOwner, Observer {
            chapterTitle = it.title
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
            partViewModel.getPartById(it.partId)
            chapterViewModel.getChapterById(it.chapterId)
            spannedString.append(partTitle + " >> " + chapterTitle + " >> " + it.title)
            val articleItem =
                ArticleItem(2, spannedString.toString(), it.foundArticle(safeArgs.query).toString(), it.id)
            models.add(articleItem)
        }
        adapter.models = models
    }
}

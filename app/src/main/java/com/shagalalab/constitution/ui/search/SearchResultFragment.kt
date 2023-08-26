package com.shagalalab.constitution.ui.search

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import by.kirich1409.viewbindingdelegate.viewBinding
import com.shagalalab.constitution.R
import com.shagalalab.constitution.data.DataHolder
import com.shagalalab.constitution.data.Language
import com.shagalalab.constitution.data.models.ArticleModel
import com.shagalalab.constitution.databinding.FragmentSearchResultBinding
import com.shagalalab.constitution.ui.article.ArticleViewModel
import com.shagalalab.constitution.ui.search.adapter.ArticleItem
import com.shagalalab.constitution.ui.search.adapter.LangItem
import com.shagalalab.constitution.ui.search.adapter.ListItem
import com.shagalalab.constitution.ui.search.adapter.ResultsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchResultFragment : Fragment(R.layout.fragment_search_result) {
    private val binding by viewBinding(FragmentSearchResultBinding::bind)

    private var adapter: ResultsAdapter = ResultsAdapter()
    private val safeArgs: SearchResultFragmentArgs by navArgs()
    private val viewModel: ArticleViewModel by viewModel()
    private lateinit var navController: NavController
    private val models: MutableList<ListItem> = mutableListOf()
    private var currentLanguageId = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvResults.text = when (safeArgs.lang) {
            Language.QQ.ordinal -> getString(R.string.qq_results_for, safeArgs.query)
            Language.RU.ordinal -> getString(R.string.ru_results_for, safeArgs.query)
            Language.UZ.ordinal -> getString(R.string.uz_results_for, safeArgs.query)
            Language.EN.ordinal -> getString(R.string.en_results_for, safeArgs.query)
            else -> getString(R.string.qq_results_for, safeArgs.query)
        }

        navController = Navigation.findNavController(view)
        val query = safeArgs.query
        binding.resultsList.adapter = adapter
        binding.resultsList.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        adapter.setItemClick {
            val action = SearchResultFragmentDirections.actionSearchResultFragmentToFoundArticle(
                queryWord = query,
                title = it.title,
                id = it.id,
            )
            navController.navigate(action)
        }
        viewModel.findArticlesByWord(query)
        viewModel.articleList.observe(viewLifecycleOwner) {
            setData(it)
        }
    }

    private fun detectHyperLinkLang(lang: Int) {
        val hyperlink = when (lang) {
            Language.QQ.ordinal -> "artqa"
            Language.RU.ordinal -> "назад"
            Language.UZ.ordinal -> "orqaga"
            Language.EN.ordinal -> "back"
            else -> "artqa"
        }
        detectNoResultMsgLang(lang, hyperlink)
    }

    private fun detectNoResultMsgLang(lang: Int, hyperlink: String) {
        val noResultMsg = when (lang) {
            Language.QQ.ordinal -> getString(R.string.no_results_qq, safeArgs.query, hyperlink)
            Language.RU.ordinal -> getString(R.string.no_results_ru, safeArgs.query, hyperlink)
            Language.UZ.ordinal -> getString(R.string.no_results_uz, safeArgs.query, hyperlink)
            Language.EN.ordinal -> getString(R.string.no_results_en, safeArgs.query, hyperlink)
            else -> getString(R.string.no_results_qq, safeArgs.query, hyperlink)
        }
        setText(noResultMsg, hyperlink)
    }

    private fun setText(noResultMsg: String, hyperlink: String) {
        val spannedString = SpannableString(noResultMsg)
        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                navController.popBackStack()
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = Color.BLUE
            }
        }
        spannedString.setSpan(
            clickableSpan,
            spannedString.indexOf(hyperlink),
            spannedString.indexOf(hyperlink) + hyperlink.length,
            SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding.tvNoResultsMsg.text = spannedString
        binding.tvNoResultsMsg.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun setData(data: List<ArticleModel>) {
        checkData(data)
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

    private fun checkData(data: List<ArticleModel>) {
        if (data.isEmpty()) {
            detectHyperLinkLang(safeArgs.lang)
            binding.tvResults.visibility = View.GONE
            binding.tvNoResultsMsg.visibility = View.VISIBLE
        } else {
            binding.tvResults.visibility = View.VISIBLE
            binding.tvNoResultsMsg.visibility = View.GONE
        }
    }
}

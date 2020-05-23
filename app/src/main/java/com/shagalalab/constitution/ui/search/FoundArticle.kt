package com.shagalalab.constitution.ui.search

import android.os.Bundle
import android.view.View
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.shagalalab.constitution.R
import com.shagalalab.constitution.ui.article.ArticleViewModel
import kotlinx.android.synthetic.main.fragment_article.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FoundArticle : Fragment(R.layout.fragment_article) {

    private val viewModel: ArticleViewModel by viewModel()
    private val safeArg: FoundArticleArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getArticleById(safeArg.id)
        viewModel.article.observe(viewLifecycleOwner, Observer {
            textView.text = HtmlCompat.fromHtml(
                it.selectedArticle(safeArg.queryWord),
                HtmlCompat.FROM_HTML_MODE_LEGACY
            )
        })
    }
}

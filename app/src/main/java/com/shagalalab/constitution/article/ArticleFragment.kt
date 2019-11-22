package com.shagalalab.constitution.article

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.shagalalab.constitution.R
import com.shagalalab.constitution.article.adapter.ArticleAdapter
import com.shagalalab.constitution.data.ConstitutionDatabase
import kotlinx.android.synthetic.main.fragment_article.*

class ArticleFragment(private var chapterId: Int) : Fragment(R.layout.fragment_article) {

    companion object {
        const val TAG = "ArticleFragment"
    }

    lateinit var viewModel: ArticleViewModel
    private val adapter = ArticleAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            ArticleViewModel(ConstitutionDatabase.getInstance(requireContext()).articleDao())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list_article.adapter = adapter
        list_article.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        viewModel.getArticles(chapterId)
        viewModel.articleList.observe(this, Observer {
            adapter.setData(it)
        })
    }
}

package com.shagalalab.constitution.article

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.method.ScrollingMovementMethod
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.shagalalab.constitution.article.adapter.ArticleAdapter
import com.shagalalab.constitution.data.ConstitutionDatabase
import kotlinx.android.synthetic.main.fragment_article.*

class ArticleFragment(private var chapterId: Int, private var check: Boolean) :
    Fragment(com.shagalalab.constitution.R.layout.fragment_article) {

    companion object {
        const val TAG = "ArticleFragment"
    }

    private lateinit var viewModel: ArticleViewModel
    private lateinit var viewModelFactory: ArticleViewModelFactory
    private val adapter = ArticleAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelFactory =
            ArticleViewModelFactory(ConstitutionDatabase.getInstance(requireContext()).articleDao())
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ArticleViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView.movementMethod = ScrollingMovementMethod()
        val s = SpannableStringBuilder()
        if (check) {
            viewModel.getArticles(chapterId)
        } else {
            viewModel.getArticlesByPartId(chapterId)
        }
        viewModel.articleList.observe(this, Observer { list ->
            list.forEach {
                s.append(it.normalizedDescription())
            }
            textView.text = s
        })
    }
}

package com.shagalalab.constitution.article

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.method.ScrollingMovementMethod
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.shagalalab.constitution.R
import com.shagalalab.constitution.data.ConstitutionDatabase
import kotlinx.android.synthetic.main.fragment_article.*

class ArticleFragment : Fragment(R.layout.fragment_article) {

    private val safeArgs: ArticleFragmentArgs by navArgs()
    private val chapterId by lazy { safeArgs.id }
    private val check by lazy { safeArgs.check }
    private val viewModelFactory by lazy {
        ArticleViewModelFactory(ConstitutionDatabase.getInstance(requireContext()).articleDao())
    }

    private lateinit var viewModel: ArticleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        viewModel.articleList.observe(viewLifecycleOwner, Observer { list ->
            list.forEach {
                s.append(it.normalizedDescription())
            }
            textView.text = s
        })
    }
}

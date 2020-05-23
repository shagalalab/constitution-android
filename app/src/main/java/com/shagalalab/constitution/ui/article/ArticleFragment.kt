package com.shagalalab.constitution.ui.article

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.method.ScrollingMovementMethod
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.shagalalab.constitution.R
import kotlinx.android.synthetic.main.fragment_article.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ArticleFragment : Fragment(R.layout.fragment_article) {

    private val safeArgs: ArticleFragmentArgs by navArgs()
    private val chapterId by lazy { safeArgs.id }
    private val check by lazy { safeArgs.check }

    private val viewModel: ArticleViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView.movementMethod = ScrollingMovementMethod()
        val s = SpannableStringBuilder()
        if (check) {
            viewModel.getArticlesByChapterId(chapterId)
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

package com.shagalalab.constitution.article

import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.method.ScrollingMovementMethod
import android.text.style.StyleSpan
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.shagalalab.constitution.data.ConstitutionDatabase
import kotlinx.android.synthetic.main.fragment_article.*


class ArticleFragment(private var chapterId: Int) :
    Fragment(com.shagalalab.constitution.R.layout.fragment_article) {

    companion object {
        const val TAG = "ArticleFragment"
    }

    lateinit var viewModel: ArticleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            ArticleViewModel(ConstitutionDatabase.getInstance(requireContext()).articleDao())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getArticles(chapterId)
        viewModel.articleList.observe(this, Observer { list ->
            val s = SpannableStringBuilder()
            list.forEach {
                val st = StyleSpan(Typeface.BOLD)
                s.append(it.title, st, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                s.append(". ")
                s.append(it.description.replace("\\n", "\n"))
                s.append("\n")
            }
            textView.text = s
            textView.movementMethod = ScrollingMovementMethod()
        })
    }
}

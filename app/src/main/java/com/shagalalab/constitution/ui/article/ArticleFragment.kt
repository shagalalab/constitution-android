package com.shagalalab.constitution.ui.article

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.method.ScrollingMovementMethod
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.shagalalab.constitution.R
import com.shagalalab.constitution.databinding.FragmentArticleBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ArticleFragment : Fragment(R.layout.fragment_article) {
    private val binding by viewBinding(FragmentArticleBinding::bind)

    private val safeArgs: ArticleFragmentArgs by navArgs()
    private val chapterId by lazy { safeArgs.id }
    private val check by lazy { safeArgs.check }

    private val viewModel: ArticleViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textView.movementMethod = ScrollingMovementMethod()
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
            binding.textView.text = s
        })
    }
}

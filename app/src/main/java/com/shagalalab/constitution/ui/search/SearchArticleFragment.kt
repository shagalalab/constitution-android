package com.shagalalab.constitution.ui.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.shagalalab.constitution.R
import com.shagalalab.constitution.databinding.FragmentArticleBinding
import com.shagalalab.constitution.ui.article.ArticleViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchArticleFragment : Fragment(R.layout.fragment_article) {
    private val binding by viewBinding(FragmentArticleBinding::bind)

    private val viewModel: ArticleViewModel by viewModel()
    private val safeArg: SearchArticleFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getArticleById(safeArg.id)
        viewModel.article.observe(viewLifecycleOwner) {
            binding.textView.text = it.highlightKeyword(safeArg.queryWord)
        }
    }
}

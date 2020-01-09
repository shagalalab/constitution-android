package com.shagalalab.constitution.part

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import com.shagalalab.constitution.MainActivity
import com.shagalalab.constitution.R
import com.shagalalab.constitution.article.ArticleFragment
import com.shagalalab.constitution.chapter.ChapterFragment
import com.shagalalab.constitution.data.ConstitutionDatabase
import com.shagalalab.constitution.part.adapter.ItemClickListener
import com.shagalalab.constitution.part.adapter.PartAdapter
import kotlinx.android.synthetic.main.fragment_part.*

class PartFragment(private var lang: Int) : Fragment(R.layout.fragment_part), ItemClickListener {

    companion object {
        const val TAG = "PartFragment"
    }

    private lateinit var viewModel: PartViewModel
    private val adapter = PartAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = PartViewModel(
            ConstitutionDatabase.getInstance(requireContext()).partDao(),
            ConstitutionDatabase.getInstance(requireContext()).chapterDao(),
            ConstitutionDatabase.getInstance(requireContext()).articleDao()
        )
        viewModel.chapterClickResult.observe(this, Observer {
            (activity as MainActivity).changeFragment(ChapterFragment(it), TAG)
        })
        viewModel.preambleClickResult.observe(this, Observer {
            (activity as MainActivity).changeFragment(ArticleFragment(it, false), TAG)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list.adapter = adapter
        list.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        viewModel.getPartsByLangId(lang)
        viewModel.partList.observe(this, Observer {
            adapter.setData(it)
        })
    }

    override fun onItemClick(id: Int) {
        viewModel.getChapterScreen(id)
    }
}

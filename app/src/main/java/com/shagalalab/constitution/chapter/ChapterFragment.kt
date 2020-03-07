package com.shagalalab.constitution.chapter

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.shagalalab.constitution.MainActivity
import com.shagalalab.constitution.R
import com.shagalalab.constitution.article.ArticleFragment
import com.shagalalab.constitution.chapter.adapter.ChapterAdapter
import com.shagalalab.constitution.chapter.adapter.ItemClickListener
import com.shagalalab.constitution.data.ConstitutionDatabase
import com.shagalalab.constitution.data.models.ChapterModel
import kotlinx.android.synthetic.main.fragment_chapter.*

class ChapterFragment(private val partId: Int) : Fragment(R.layout.fragment_chapter),
    ItemClickListener {

    companion object {
        const val TAG = "ChapterFragment"
    }

    private val adapter = ChapterAdapter(this)
    private lateinit var viewModel: ChapterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ChapterViewModel(
            ConstitutionDatabase.getInstance(requireContext()).chapterDao()
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list_chapter.adapter = adapter
        list_chapter.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        viewModel.getChaptersByPartId(partId)
        viewModel.chapterList.observe(this, Observer {
            adapter.setData(it)
        })
    }

    override fun onItemClick(model: ChapterModel) {
        (activity as MainActivity).changeFragment(ArticleFragment(model.id, true), TAG)
    }
}

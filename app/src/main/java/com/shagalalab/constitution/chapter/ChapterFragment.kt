package com.shagalalab.constitution.chapter

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.shagalalab.constitution.R
import com.shagalalab.constitution.chapter.adapter.ChapterAdapter
import com.shagalalab.constitution.chapter.adapter.ItemClickListener
import com.shagalalab.constitution.data.ConstitutionDatabase
import com.shagalalab.constitution.data.models.ChapterModel
import kotlinx.android.synthetic.main.fragment_chapter.*

class ChapterFragment(private val partId: Int) : Fragment(R.layout.fragment_chapter),
    ItemClickListener {

    private val adapter = ChapterAdapter(this)
    private lateinit var viewModel: ChapterViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list_chapter.adapter = adapter
        list_chapter.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        viewModel = ChapterViewModel(
            ConstitutionDatabase.getInstance(requireContext()).chapterDao()
        )
        viewModel.getChaptersByPartId(partId)
        viewModel.chapterList.observe(this, Observer {
            adapter.setData(it)
        })
    }

    override fun onItemClick(model: ChapterModel) {
        Toast.makeText(context, model.title, Toast.LENGTH_SHORT).show()
    }
}

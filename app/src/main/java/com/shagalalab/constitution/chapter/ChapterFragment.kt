package com.shagalalab.constitution.chapter

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import com.shagalalab.constitution.R
import com.shagalalab.constitution.chapter.adapter.ChapterAdapter
import com.shagalalab.constitution.chapter.adapter.ItemClickListener
import com.shagalalab.constitution.data.ConstitutionDatabase
import com.shagalalab.constitution.data.models.ChapterModel
import kotlinx.android.synthetic.main.fragment_chapter.*

class ChapterFragment : Fragment(R.layout.fragment_chapter),
    ItemClickListener {

    companion object {
        const val TAG = "ChapterFragment"
    }

    private var partId = 0
    private val adapter = ChapterAdapter(this)
    private lateinit var viewModel: ChapterViewModel
    private lateinit var navController: NavController
    private val safeArgs: ChapterFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        partId = safeArgs.id
        viewModel = ViewModelProviders.of(
            this,
            ChapterViewModelFactory(ConstitutionDatabase.getInstance(requireContext()).chapterDao())
        ).get(ChapterViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        list_chapter.adapter = adapter
        list_chapter.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        viewModel.getChaptersByPartId(partId)
        viewModel.chapterList.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })
    }

    override fun onItemClick(model: ChapterModel) {
        changeToArticleFragment(model.id)
//        (activity as MainActivity).changeFragment(ArticleFragment(model.id, true), TAG)
    }

    private fun changeToArticleFragment(id: Int) {
        val action = ChapterFragmentDirections.actionChapterFragmentToArticleFragment(id, true)
        navController.navigate(action)
    }
}

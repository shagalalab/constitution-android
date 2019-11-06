package com.shagalalab.constitution.part

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.shagalalab.constitution.MainActivity
import com.shagalalab.constitution.R
import com.shagalalab.constitution.chapter.ChapterFragment
import com.shagalalab.constitution.data.ConstitutionDatabase
import com.shagalalab.constitution.data.models.PartModel
import com.shagalalab.constitution.part.adapter.ItemClickListener
import com.shagalalab.constitution.part.adapter.PartAdapter
import kotlinx.android.synthetic.main.fragment_part.*

class PartFragment(private var lang: Int) : Fragment(R.layout.fragment_part), ItemClickListener {

    companion object {
        const val TAG = "PartFragment"
    }

    private lateinit var viewModel: PartViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = PartAdapter(this)
        list.adapter = adapter
        list.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        viewModel = PartViewModel(
            ConstitutionDatabase.getInstance(requireContext()).partDao(),
            ConstitutionDatabase.getInstance(requireContext()).chapterDao()
        )
        viewModel.getPartsByLangId(lang)
        viewModel.partList.observe(this, Observer {
            adapter.setData(it)
        })
        viewModel.modelLiveData.observe(this, Observer {
            if (it.second) {
                (activity as MainActivity).changeFragment(ChapterFragment(it.first), TAG)
            } else Toast.makeText(context, "PartId ${it.first}", Toast.LENGTH_SHORT).show()
        })
    }

    override fun onItemClick(model: PartModel) {
        viewModel.getChapterScreen(model.id)
    }
}
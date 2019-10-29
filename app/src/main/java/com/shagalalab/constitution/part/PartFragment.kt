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
        const val SEVEN = 7
        const val ONE = 1
        const val ZERO = 0
        const val TAG = "PartFragment"
    }

    private lateinit var viewModel: PartViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = PartAdapter(this)
        list.adapter = adapter
        list.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        viewModel = PartViewModel(
            ConstitutionDatabase.getInstance(requireContext()).partDao()
        )
        viewModel.getPartsByLangId(lang)
        viewModel.partListToObserveOutside.observe(this, Observer {
            adapter.setData(it)
        })
    }

    override fun onItemClick(model: PartModel) {
        if (model.id % SEVEN == ONE || model.id % SEVEN == ZERO)
            Toast.makeText(context, model.title, Toast.LENGTH_SHORT).show()
        else {
            (activity as MainActivity).changeFragment(ChapterFragment(model.id), TAG)
        }
    }
}

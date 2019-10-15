package com.shagalalab.constitution.list

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.shagalalab.constitution.R
import com.shagalalab.constitution.data.ConstitutionDatabase
import com.shagalalab.constitution.data.models.PartModel
import com.shagalalab.constitution.list.adapter.ItemClickListener
import com.shagalalab.constitution.list.adapter.ListAdapter
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment(private var lang: Int) : Fragment(R.layout.fragment_list), ItemClickListener {

    private lateinit var viewModel: ListViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ListAdapter(this)
        list.adapter = adapter
        list.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        viewModel = ListViewModel(
            ConstitutionDatabase.getInstance(context!!).partDao(),
            ConstitutionDatabase.getInstance(context!!).chapterDao(),
            ConstitutionDatabase.getInstance(context!!).articleDao()
        )
        viewModel.getPartsByLangId(lang)
        viewModel.partList.observe(this, Observer {
            adapter.setData(it)
        })
    }

    override fun onItemClick(model: PartModel) {
        Toast.makeText(context, model.title, Toast.LENGTH_SHORT).show()
    }
}

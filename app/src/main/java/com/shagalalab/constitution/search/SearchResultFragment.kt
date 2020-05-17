package com.shagalalab.constitution.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.shagalalab.constitution.R
import com.shagalalab.constitution.search.adapter.ResultsAdapter
import kotlinx.android.synthetic.main.fragment_search_result.*

class SearchResultFragment : Fragment(R.layout.fragment_search_result) {

    private var adapter: ResultsAdapter = ResultsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        resultsList.adapter = adapter
        resultsList.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        super.onCreate(savedInstanceState)
    }
}

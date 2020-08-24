package com.shagalalab.constitution.ui.base

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.shagalalab.constitution.R

open class SearchableFragment(resId: Int) : Fragment(resId) {

    private lateinit var submitText: (text: String) -> Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        val searchItem: MenuItem = menu.findItem(R.id.menu)
        val searchView: SearchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                submitText.invoke(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        }
        )
    }

    fun setSubmitText(submitText: (text: String) -> Unit) {
        this.submitText = submitText
    }
}

package com.shagalalab.constitution.ui.base

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.Fragment
import com.shagalalab.constitution.R

open class SearchableFragment(resId: Int) : Fragment(resId) {

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    private lateinit var submitText: (text: String?) -> Unit

    fun setSubmitText(submitText: (text: String?) -> Unit) {
        this.submitText = submitText
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        val searchItem: MenuItem = menu.findItem(R.id.menu)
        val searchView: SearchView =
            MenuItemCompat.getActionView(searchItem) as SearchView
        val id = searchView.context.resources.getIdentifier(
            "androidx.appcompat.R.id.search_src_text",
            null,
            null
        )
        searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                submitText.invoke(p0)
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        }
        )
        super.onCreateOptionsMenu(menu, inflater)
    }
}

package com.shagalalab.constitution.ui.base

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.shagalalab.constitution.R

open class SearchableFragment(resId: Int) : Fragment(resId) {

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    private lateinit var submitText: (text: String) -> Unit

    fun setSubmitText(submitText: (text: String) -> Unit) {
        this.submitText = submitText
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        val searchItem: MenuItem = menu.findItem(R.id.menu)
        val searchView: SearchView =
            searchItem.actionView as SearchView
        changeSearchViewTextColor(searchView)
        searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String): Boolean {
                submitText.invoke(p0)
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        }
        )
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun changeSearchViewTextColor(view: View?) {
        if (view != null) {
            if (view is TextView) {
                view.setTextColor(Color.WHITE)
                return
            } else if (view is ViewGroup) {
                for (i in 0 until view.childCount) {
                    changeSearchViewTextColor(view.getChildAt(i))
                }
            }
        }
    }
}

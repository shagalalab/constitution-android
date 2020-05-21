package com.shagalalab.constitution.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.room.InvalidationTracker
import com.shagalalab.constitution.R
import com.shagalalab.constitution.article.ArticleViewModel
import com.shagalalab.constitution.data.Language
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var navController: NavController
    private val viewModel: ArticleViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        en_flag.setOnClickListener {
            chooseLanguage(Language.EN.ordinal)
        }
        en_text.setOnClickListener {
            chooseLanguage(Language.EN.ordinal)
        }
        qq_flag.setOnClickListener {
            chooseLanguage(Language.QQ.ordinal)
        }
        qq_text.setOnClickListener {
            chooseLanguage(Language.QQ.ordinal)
        }
        uz_flag.setOnClickListener {
            chooseLanguage(Language.UZ.ordinal)
        }
        uz_text.setOnClickListener {
            chooseLanguage(Language.UZ.ordinal)
        }
        ru_flag.setOnClickListener {
            chooseLanguage(Language.RU.ordinal)
        }
        ru_text.setOnClickListener {
            chooseLanguage(Language.RU.ordinal)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu);
        val searchItem: MenuItem = menu.findItem(R.id.menu);
        val searchView: SearchView =
            MenuItemCompat.getActionView(searchItem) as SearchView
        searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                val action = MainFragmentDirections.actionMainFragmentToSearchResultFragment(query)
                navController.navigate(action)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                val action = MainFragmentDirections.actionMainFragmentToSearchResultFragment(newText)
                //navController.navigate(action)
                return false
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun chooseLanguage(lang: Int) {
        val title = when (lang) {
            Language.QQ.ordinal -> "Тараўлар"
            Language.RU.ordinal -> "Разделы"
            Language.UZ.ordinal -> "Bo`limlar"
            Language.EN.ordinal -> "Parts"
            else -> ""
        }
        val action = MainFragmentDirections.actionMainFragmentToPartFragment(lang, title)
        navController.navigate(action)
    }
}

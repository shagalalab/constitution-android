package com.shagalalab.constitution

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuItemCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //private var viewModel: ArticleViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController = findNavController(R.id.container)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setSupportActionBar(toolbar)
        toolbar.setupWithNavController(navController, appBarConfiguration)
        //  handleIntent(intent)
    }
//
//    private fun handleIntent(intent: Intent) {
//        if (Intent.ACTION_SEARCH == intent.action) {
//            val query = intent.getStringExtra(SearchManager.QUERY)
//            //viewModel.findArticlesByWord(query)
//        }
//    }
//
//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
////        menuInflater.inflate(R.menu.menu, menu)
////        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
////        (menu.findItem(R.id.menu).actionView as SearchView).apply {
////            setSearchableInfo(searchManager.getSearchableInfo(componentName))
////        }
//        menuInflater.inflate(R.menu.menu, menu);
//        val searchItem: MenuItem = menu.findItem(R.id.menu);
//        val searchView: SearchView =
//            MenuItemCompat.getActionView(searchItem) as SearchView
//        searchView.setOnQueryTextListener(object :
//            SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String): Boolean {
//                //Perform the final search
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String): Boolean {
//                //Text has changed, apply filtering?
//                return false
//            }
//        })
//        return true
//    }
}

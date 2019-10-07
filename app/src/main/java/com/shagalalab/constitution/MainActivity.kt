package com.shagalalab.constitution

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.shagalalab.constitution.data.ConstitutionDatabase
import com.shagalalab.constitution.main.MainFragment
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Executors.newSingleThreadExecutor().execute {
            val db = ConstitutionDatabase.getInstance(this)
            println("mytest, parts size = ${db.partDao().getParts().size}")
            println("mytest, chapters size = ${db.chapterDao().getChapters().size}")
            println("mytest, articles size = ${db.articleDao().getArticles().size}")
        }
        changeFragment(MainFragment(), MainFragment.TAG)
    }

    fun changeFragment(fragment: Fragment, tag: String) {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.container, fragment, tag)
            .addToBackStack(tag)
            .commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
            return
        }
        super.onBackPressed()
    }
}

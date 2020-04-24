package com.shagalalab.constitution

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.shagalalab.constitution.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        changeFragment(MainFragment(), MainFragment.TAG)
    }

//    private fun changeFragment(fragment: Fragment, tag: String) {
//        supportFragmentManager
//            .beginTransaction()
//            .replace(R.id.container, fragment, tag)
//            .addToBackStack(tag)
//            .commit()
//    }
//
//    override fun onBackPressed() {
//        if (supportFragmentManager.backStackEntryCount == 1) {
//            finish()
//            return
//        }
//        super.onBackPressed()
//    }
}

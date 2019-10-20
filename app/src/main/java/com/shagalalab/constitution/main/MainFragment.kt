package com.shagalalab.constitution.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shagalalab.constitution.MainActivity
import com.shagalalab.constitution.list.ListFragment
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    companion object {
        const val TAG = "MainFragment"
        const val QQ = 1
        const val RU = 2
        const val UZ = 3
        const val EN = 4
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            com.shagalalab.constitution.R.layout.fragment_main,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        en_flag.setOnClickListener {
            showMessage(EN)
        }
        en_text.setOnClickListener {
            showMessage(EN)
        }
        qq_flag.setOnClickListener {
            showMessage(QQ)
        }
        qq_text.setOnClickListener {
            showMessage(QQ)
        }
        uz_flag.setOnClickListener {
            showMessage(UZ)
        }
        uz_text.setOnClickListener {
            showMessage(UZ)
        }
        ru_flag.setOnClickListener {
            showMessage(RU)
        }
        ru_text.setOnClickListener {
            showMessage(RU)
        }
    }

    private fun showMessage(lang: Int) {
        (activity as MainActivity).changeFragment(ListFragment(lang), TAG)
    }
}

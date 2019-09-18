package com.shagalalab.constitution.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shagalalab.constitution.R
import com.shagalalab.constitution.list.ListFragment
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        en_flag.setOnClickListener {
            showMessage(en_text.text.toString())
        }
        en_text.setOnClickListener {
            showMessage(en_text.text.toString())
        }
        qq_flag.setOnClickListener {
            showMessage(qq_text.text.toString())
        }
        qq_text.setOnClickListener {
            showMessage(qq_text.text.toString())
        }
        uz_flag.setOnClickListener {
            showMessage(uz_text.text.toString())
        }
        uz_text.setOnClickListener {
            showMessage(uz_text.text.toString())
        }
        ru_flag.setOnClickListener {
            showMessage(ru_text.text.toString())
        }
        ru_text.setOnClickListener {
            showMessage(ru_text.text.toString())
        }
    }

    private fun showMessage(lang: String) {
        val intent = Intent(context, ListFragment::class.java)
        intent.putExtra("lang", lang)
        startActivity(intent)
    }
}

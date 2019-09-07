package com.shagalalab.constitution

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
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
            showMessage()
        }
        en_text.setOnClickListener {
            showMessage()
        }
        qq_flag.setOnClickListener {
            showMessage()
        }
        qq_text.setOnClickListener {
            showMessage()
        }
        uz_flag.setOnClickListener {
            showMessage()
        }
        uz_text.setOnClickListener {
            showMessage()
        }
        ru_flag.setOnClickListener {
            showMessage()
        }
        ru_text.setOnClickListener {
            showMessage()
        }
    }
    private fun showMessage(){
        Toast.makeText(activity, "Clicked", Toast.LENGTH_SHORT).show()
    }
}


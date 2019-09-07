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
        en_flag.setOnClickListener {
            Toast.makeText(activity?.applicationContext,"Clicked", Toast.LENGTH_SHORT).show()
        }
        return inflater.inflate(R.layout.fragment_main, container, false)
    }
}


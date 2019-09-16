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
            showMessage("en")
        }
        en_text.setOnClickListener {
            showMessage("en")
        }
        qq_flag.setOnClickListener {
            showMessage("qq")
        }
        qq_text.setOnClickListener {
            showMessage("qq")
        }
        uz_flag.setOnClickListener {
            showMessage("uz")
        }
        uz_text.setOnClickListener {
            showMessage("uz")
        }
        ru_flag.setOnClickListener {
            showMessage("ru")
        }
        ru_text.setOnClickListener {
            showMessage("ru")
        }
    }

    private fun showMessage(lang: String) {
        if (lang == "en") {
            Toast.makeText(activity, "EN Clicked", Toast.LENGTH_SHORT).show()
        }
        if (lang == "ru") {
            Toast.makeText(activity, "RU Clicked", Toast.LENGTH_SHORT).show()
        }
        if (lang == "qq") {
            Toast.makeText(activity, "QQ Clicked", Toast.LENGTH_SHORT).show()
        }
        if (lang == "uz") {
            Toast.makeText(activity, "UZ Clicked", Toast.LENGTH_SHORT).show()
        }
    }
}

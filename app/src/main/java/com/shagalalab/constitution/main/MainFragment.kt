package com.shagalalab.constitution.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.shagalalab.constitution.R
import com.shagalalab.constitution.data.Language
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var navController: NavController

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

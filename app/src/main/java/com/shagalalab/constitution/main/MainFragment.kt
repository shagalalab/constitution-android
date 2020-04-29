package com.shagalalab.constitution.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.shagalalab.constitution.R
import com.shagalalab.constitution.data.Language
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        en_flag.setOnClickListener {
            chooseLanguage(Language.EN)
        }
        en_text.setOnClickListener {
            chooseLanguage(Language.EN)
        }
        qq_flag.setOnClickListener {
            chooseLanguage(Language.QQ)
        }
        qq_text.setOnClickListener {
            chooseLanguage(Language.QQ)
        }
        uz_flag.setOnClickListener {
            chooseLanguage(Language.UZ)
        }
        uz_text.setOnClickListener {
            chooseLanguage(Language.UZ)
        }
        ru_flag.setOnClickListener {
            chooseLanguage(Language.RU)
        }
        ru_text.setOnClickListener {
            chooseLanguage(Language.RU)
        }
    }

    private fun chooseLanguage(lang: Language) {
        val title = when (lang) {
            Language.QQ -> "Тараўлар"
            Language.RU -> "Разделы"
            Language.UZ -> "Bo`limlar"
            Language.EN -> "Parts"
        }
        val action = MainFragmentDirections.actionMainFragmentToPartFragment(lang.id, title)
        navController.navigate(action)
    }
}

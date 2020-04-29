package com.shagalalab.constitution.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    private lateinit var navController: NavController

    companion object {
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
        navController = Navigation.findNavController(view)
        en_flag.setOnClickListener {
            chooseLanguage(EN)
        }
        en_text.setOnClickListener {
            chooseLanguage(EN)
        }
        qq_flag.setOnClickListener {
            chooseLanguage(QQ)
        }
        qq_text.setOnClickListener {
            chooseLanguage(QQ)
        }
        uz_flag.setOnClickListener {
            chooseLanguage(UZ)
        }
        uz_text.setOnClickListener {
            chooseLanguage(UZ)
        }
        ru_flag.setOnClickListener {
            chooseLanguage(RU)
        }
        ru_text.setOnClickListener {
            chooseLanguage(RU)
        }
    }

    private fun chooseLanguage(lang: Int) {
        var title = ""
        when (lang) {
            QQ -> title = "Тараў"
            RU -> title = "Раздел"
            UZ -> title = "Bo`lim"
            EN -> title = "Parts"
        }
        val action = MainFragmentDirections.actionMainFragmentToPartFragment(lang, title)
        navController.navigate(action)
    }
}

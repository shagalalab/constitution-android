package com.shagalalab.constitution.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.withResumed
import androidx.navigation.NavController
import androidx.navigation.Navigation
import by.kirich1409.viewbindingdelegate.viewBinding
import com.shagalalab.constitution.R
import com.shagalalab.constitution.data.Language
import com.shagalalab.constitution.databinding.FragmentMainBinding
import com.shagalalab.constitution.ui.base.SearchableFragment
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainFragment : SearchableFragment(R.layout.fragment_main) {
    private val binding by viewBinding(FragmentMainBinding::bind)

    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        binding.enFlag.setOnClickListener {
            chooseLanguage(Language.EN.ordinal)
        }
        binding.enText.setOnClickListener {
            chooseLanguage(Language.EN.ordinal)
        }
        binding.qqFlag.setOnClickListener {
            chooseLanguage(Language.QQ.ordinal)
        }
        binding.qqText.setOnClickListener {
            chooseLanguage(Language.QQ.ordinal)
        }
        binding.uzFlag.setOnClickListener {
            chooseLanguage(Language.UZ.ordinal)
        }
        binding.uzText.setOnClickListener {
            chooseLanguage(Language.UZ.ordinal)
        }
        binding.ruFlag.setOnClickListener {
            chooseLanguage(Language.RU.ordinal)
        }
        binding.ruText.setOnClickListener {
            chooseLanguage(Language.RU.ordinal)
        }

        searchText.onEach { query ->
            if (query.isNotEmpty()) {
                val action = MainFragmentDirections.actionMainFragmentToSearchResultFragment(
                    query = query,
                    title = "Izlew nátiyjeleri"
                )
                navController.navigate(action)
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
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

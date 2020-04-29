package com.shagalalab.constitution.part

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import com.shagalalab.constitution.R
import com.shagalalab.constitution.data.ConstitutionDatabase
import com.shagalalab.constitution.part.adapter.ItemClickListener
import com.shagalalab.constitution.part.adapter.PartAdapter
import kotlinx.android.synthetic.main.fragment_part.*

class PartFragment : Fragment(R.layout.fragment_part), ItemClickListener {

    companion object {
        const val TAG = "PartFragment"
        const val QQ = 1
        const val RU = 2
        const val UZ = 3
        const val EN = 4
    }

    private lateinit var navController: NavController
    private val adapter = PartAdapter(this)
    private val safeArgs: PartFragmentArgs by navArgs()
    private lateinit var viewModel: PartViewModel
    private var lang: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lang = safeArgs.lang
        viewModel = ViewModelProviders.of(
            this, PartViewModelFactory(
                ConstitutionDatabase.getInstance(requireContext()).partDao(),
                ConstitutionDatabase.getInstance(requireContext()).chapterDao(),
                ConstitutionDatabase.getInstance(requireContext()).articleDao()
            )
        ).get(PartViewModel::class.java)
        viewModel.chapterClickResult.observe(this, Observer {
            changeToChapterFragment(it)
        })
        viewModel.preambleClickResult.observe(this, Observer {
            changeToArticleFragment(it)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        list.adapter = adapter
        list.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        viewModel.getPartsByLangId(lang)
        viewModel.partList.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })
    }

    override fun onItemClick(id: Int) {
        viewModel.getChapterScreen(id)
    }

    private fun changeToChapterFragment(id: Int) {
        val action = PartFragmentDirections.actionPartFragmentToChapterFragment(
            chooseTitleLang(lang),
            id,
            lang
        )
        navController.navigate(action)
    }

    private fun changeToArticleFragment(id: Int) {
        val action = PartFragmentDirections.actionPartFragmentToArticleFragment(
            chooseTitleLang(lang),
            id,
            false
        )
        navController.navigate(action)
    }

    private fun chooseTitleLang(langCode: Int): String {
        var title = ""
        when (langCode) {
            QQ -> title = "Бап"
            RU -> title = "Глава"
            UZ -> title = "Bob"
            EN -> title = "Chapters"
        }
        return title
    }
}

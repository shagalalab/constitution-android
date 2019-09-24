package com.shagalalab.constitution.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.shagalalab.constitution.R
import com.shagalalab.constitution.list.adapter.ItemClickListener
import com.shagalalab.constitution.list.adapter.ListAdapter
import com.shagalalab.constitution.list.model.Item
import kotlinx.android.synthetic.main.fragment_list.*
import kotlin.random.Random

class ListFragment(private var lang: String) : Fragment(), ItemClickListener {

    companion object {
        const val NUMBER_OF_ITEMS = 10
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ListAdapter(this)
        list.adapter = adapter
        list.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        adapter.setData(getData())
    }

    private fun getData(): List<Item> {
        return List(NUMBER_OF_ITEMS) {
            Item("$lang-title-$it", if (Random.nextBoolean()) "$lang-description-$it" else "")
        }
    }

    override fun onItemClick(model: Item) {
        Toast.makeText(context, model.title, Toast.LENGTH_SHORT).show()
    }
}

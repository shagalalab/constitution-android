package com.shagalalab.constitution.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.shagalalab.constitution.R
import com.shagalalab.constitution.list.adapter.ListAdapter
import com.shagalalab.constitution.list.model.Item
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : AppCompatActivity() {

    companion object{
        const val k = 10
    }
    private var lang = ""
    private val adapter = ListAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_list)
        lang = intent.getStringExtra("lang")
        list.adapter = adapter
        list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        adapter.setData(getData())
    }

    private fun getData(): MutableList<Item> {
        val result: MutableList<Item> = arrayListOf()
        result.add(Item("$lang-title-0", ""))
        for (item in 1..k) {
            result.add(Item("$lang-title-$item", "$lang-description-$item"))
        }
        return result
    }
}

package com.example.razer.exhibition

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import android.arch.lifecycle.ViewModelProviders
import com.example.myapplication.Exhibit
import com.example.razer.exhibition.adapter.ExhibitionListAdapter


class MainActivity : AppCompatActivity() {
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        initRV()
        listen()
        viewModel.requestExhibits()
    }

    private fun listen() {
        viewModel.data.observe(this,object:Observer<List<Exhibit>>{
            override fun onChanged(t: List<Exhibit>?) {
                if (t == null) return
                with (this@MainActivity.recyclerView.adapter as ExhibitionListAdapter){
                    data = t
                    notifyDataSetChanged()
                }
            }
        })
    }

    private fun initRV() {
        viewManager = LinearLayoutManager(this)
        viewAdapter = ExhibitionListAdapter(emptyList())
        this.recyclerView.apply {
            setHasFixedSize(true)

            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

}

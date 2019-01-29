package com.example.razer.exhibition.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.Exhibit
import com.example.razer.exhibition.R
import kotlinx.android.synthetic.main.item.view.*

class ExhibitionListAdapter(var data: List<Exhibit>): RecyclerView.Adapter<ExhibitionListAdapter.ExhibitViewHolder>() {

    inner class ExhibitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind(item: Exhibit) {
            Log.d("BINDING","ITEM: " + item.title + "IMAGES: " + item.images.size)
            with(itemView) {
                viewPager.adapter = null
                viewPager.adapter = ViewPagerAdapter.PageFragmentAdapter(this.context, item.images)

                type.text = item.title
            }
            Log.d("PAGING","SHOW")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExhibitViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item,parent,false)
        return ExhibitViewHolder(view)

    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ExhibitViewHolder, position: Int)
            = holder.bind(data[position])

}
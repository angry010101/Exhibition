package com.example.razer.exhibition.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.razer.exhibition.R
import kotlinx.android.synthetic.main.page_fragment.view.*


class ViewPagerAdapter {

    class PageFragmentAdapter(val context: Context,val data: List<String>) : PagerAdapter() {

        override fun instantiateItem(collection: ViewGroup, position: Int): Any {
            val inflater = LayoutInflater.from(context)
            val layout = inflater.inflate(R.layout.page_fragment, collection, false) as ViewGroup


            Glide.with(context).load(data[position]).into(layout.imageView)
            collection.addView(layout)
            return layout
        }

        override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
            collection.removeView(view as View)
        }

        override fun getCount(): Int {
            return data.size
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object`
        }

    }

}
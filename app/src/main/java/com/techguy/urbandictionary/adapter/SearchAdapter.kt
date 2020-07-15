package com.techguy.urbandictionary.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.techguy.urbandictionary.R
import com.techguy.urbandictionary.model.SearchModel
import com.techguy.urbandictionary.viewholder.SearchVH

class SearchAdapter: Adapter<SearchVH> {

    lateinit var list: List<SearchModel>

    constructor()

    constructor(listy: List<SearchModel>){
        list = listy
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchVH {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.search_results, parent, false)
        return SearchVH(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: SearchVH, position: Int) {
        if (position < list.size) {
            holder.setDownVote(list[position].downVote)
            holder.setUpVote(list[position].upVote)
            holder.setDefinition(list[position].definition)
            holder.itemView.setOnClickListener { v ->
                YoYo.with(Techniques.RubberBand).duration(500).playOn(v)
            }
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}
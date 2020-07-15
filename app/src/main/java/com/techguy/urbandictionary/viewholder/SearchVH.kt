package com.techguy.urbandictionary.viewholder

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.techguy.urbandictionary.R

lateinit var upVoteText: TextView
lateinit var downVote: TextView
lateinit var definationText: TextView

class SearchVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    init {
        upVoteText = itemView.findViewById(R.id.thumb_up_text)
        downVote = itemView.findViewById(R.id.thumb_down_text)
        definationText = itemView.findViewById(R.id.search_result)
    }

    //Check and Set UpVote
    fun setUpVote(upvote: Long) {
        upVoteText.text = upvote.toString()
    }

    //Check and Set DownVote
    fun setDownVote(downvote: Long) {
        downVote.text = downvote.toString()
    }

    //Check and Set Definition
    fun setDefinition(definition: String) {
        if (!definition.isEmpty())
            definationText.text = definition
        else
            Log.e("TOM", "Definition Empty")
    }
}


package com.techguy.urbandictionary.utils

import com.techguy.urbandictionary.model.SearchModel
import java.util.Comparator

class CompareUtils {

    // A comparator to Upvotes
    class UpvoteComparator : Comparator<SearchModel> {
        override fun compare(o1: SearchModel?, o2: SearchModel?): Int {
            if (o1 == null || o2 == null)
                return 0
            return o1.upVote.compareTo(o2.upVote)
        }
    }

    // A comparator to DownVotes
    class DownvoteComparator : Comparator<SearchModel> {
        override fun compare(o1: SearchModel?, o2: SearchModel?): Int {
            if (o1 == null || o2 == null)
                return 0
            return o1.downVote.compareTo(o2.downVote)
        }
    }
}
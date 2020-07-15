package com.techguy.urbandictionary.model

import com.orm.SugarRecord

class SearchModel : SugarRecord{

    var definition: String = ""
    var upVote: Long = 0
    var downVote: Long = 0
    var query: String = ""

    constructor()

    constructor(definition: String, upVote: Long, downVote: Long) {
        this.definition = definition
        this.upVote = upVote
        this.downVote = downVote
    }

    constructor(definition: String, upVote: Long, downVote: Long, query: String) : super() {
        this.definition = definition
        this.upVote = upVote
        this.downVote = downVote
        this.query = query
    }


}
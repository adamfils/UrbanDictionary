package com.techguy.urbandictionary.contract

import com.techguy.urbandictionary.model.SearchModel

interface SearchContract{
    fun onSearchSuccess(model: List<SearchModel>)
    fun onSearchFailure()
}
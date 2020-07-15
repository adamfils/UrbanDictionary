package com.techguy.urbandictionary.viewmodel

import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.techguy.urbandictionary.contract.SearchContract
import com.techguy.urbandictionary.model.SearchModel
import com.techguy.urbandictionary.utils.NetworkUtils


class SearchViewModel : ViewModel, SearchContract {

    var searchResults: MutableLiveData<List<SearchModel>> = MutableLiveData()
    var errorMessage: MutableLiveData<String> = MutableLiveData()

    constructor()

    fun getSearchResults(): LiveData<List<SearchModel>> {
        return searchResults
    }

    fun queryWord(query: String, progress: ProgressBar) {
        NetworkUtils().searchDefinition(query, this, progress)
    }

    fun getError(): LiveData<String> {
        return errorMessage;
    }

    override fun onSearchSuccess(model: List<SearchModel>) {
        searchResults.value = model
    }

    override fun onSearchFailure() {
        errorMessage.value = "Query Failed"
    }
}
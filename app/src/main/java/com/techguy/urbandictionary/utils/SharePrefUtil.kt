package com.techguy.urbandictionary.utils

import android.content.Context
import android.content.SharedPreferences

open class SharePrefUtil {

    //Save Last Query To Shared Preferences
    open fun setHistory(context: Context, value: String) {
        val rateEditor =
            context.getSharedPreferences("history", Context.MODE_PRIVATE).edit()
        rateEditor.putString("query", value)
        rateEditor.apply()
    }

    //Get Last Query From Shared Preferences
    open fun getHistory(context: Context): String {
        val showPref =
            context.getSharedPreferences("history", Context.MODE_PRIVATE)
        //Return Lol by Default
        return showPref.getString("query", "lol").toString()
    }

}
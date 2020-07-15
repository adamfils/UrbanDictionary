package com.techguy.urbandictionary.utils

import android.util.Log
import com.orm.SugarRecord
import com.techguy.urbandictionary.model.SearchModel
import java.util.regex.Pattern

class DBUtils {

    fun searchDB(query: String): List<SearchModel> {
        //Delete Local Cache If It Exceeds 50 Entries
        if (getDB().size > 50) {
            DBUtils().deleteAll()
        }
        val resultArray: ArrayList<SearchModel> = ArrayList()
        for (i in getDB().indices) {
            //Check If Query Exists In Local DB
            if (getDB()[i].query == query) {
                resultArray.add(
                    SearchModel(
                        getDB()[i].definition,
                        getDB()[i].upVote,
                        getDB()[i].downVote,
                        query
                    )
                )
            }
        }
        return resultArray

    }

    fun getDB(): List<SearchModel> {
        //Return All DB Records
        return SugarRecord.listAll(SearchModel::class.java)
    }

    fun deleteAll() {
        //Delete All DB Records
        SugarRecord.deleteAll(SearchModel::class.java)
    }

}
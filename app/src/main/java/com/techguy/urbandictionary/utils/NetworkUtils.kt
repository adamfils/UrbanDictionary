package com.techguy.urbandictionary.utils

import android.view.View
import android.widget.ProgressBar
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.techguy.urbandictionary.contract.SearchContract
import com.techguy.urbandictionary.model.SearchModel
import org.json.JSONObject
import org.koin.core.context.KoinContextHandler.get

class NetworkUtils {

    lateinit var model: ArrayList<SearchModel>

    fun searchDefinition(word: String, listener: SearchContract, progress: ProgressBar) {
        progress.visibility = View.VISIBLE
        val result: List<SearchModel> = DBUtils().searchDB(word)
        if (result.isEmpty()) {
            //No Cache Exist Loading From The Web
            AndroidNetworking.get(Constant.SEARCH_PREFIX + word)
                .addHeaders("x-rapidapi-host", Constant.SEARCH_CLIENT)
                .addHeaders("x-rapidapi-key", Constant.APIKEY)
                .build().getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject?) {
                        progress.visibility = View.GONE
                        //Check If Response Contains An Object Called List
                        if (response?.has("list")!!) {
                            model = ArrayList()
                            val jsonArray = response.getJSONArray("list")
                            for (i in 0 until jsonArray.length()) {
                                val jObj = jsonArray.getJSONObject(i)
                                //Add Entries Into Model
                                model.add(
                                    SearchModel(
                                        jObj.getString("definition"),
                                        jObj.getLong("thumbs_up"),
                                        jObj.getLong("thumbs_down")
                                    )
                                )
                                //Save Entries To Database
                                SearchModel(
                                    jObj.getString("definition"),
                                    jObj.getLong("thumbs_up"),
                                    jObj.getLong("thumbs_down"),
                                    word
                                ).save()
                            }
                            listener.onSearchSuccess(model)
                        }
                    }

                    override fun onError(anError: ANError?) {
                        //Handle Failures
                        progress.visibility = View.GONE
                        listener.onSearchFailure()
                    }
                })
        } else {
            //Hide ProgressBar
            progress.visibility = View.GONE
            //Push Results To ViewModel
            listener.onSearchSuccess(result)
        }
    }
}
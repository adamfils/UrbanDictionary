package com.techguy.urbandictionary.view

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.github.clans.fab.FloatingActionButton
import com.techguy.urbandictionary.R
import com.techguy.urbandictionary.adapter.SearchAdapter
import com.techguy.urbandictionary.databinding.ActivityMainBinding
import com.techguy.urbandictionary.model.SearchModel
import com.techguy.urbandictionary.utils.CompareUtils
import com.techguy.urbandictionary.utils.SharePrefUtil
import com.techguy.urbandictionary.viewmodel.SearchViewModel
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), TextView.OnEditorActionListener {

    private lateinit var viewModel: SearchViewModel
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var resultList: List<SearchModel>
    private lateinit var resultList2: List<SearchModel>
    private var isUpvote: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        resultList = ArrayList()

        //Initializing DataBinder
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //Initializing ViewModel
        viewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)

        //Listen for Keyboard Search Action
        mBinding.searchBar.setOnEditorActionListener(this)

        //Query Last Query In SharePreferences
        viewModel.queryWord(SharePrefUtil().getHistory(this@MainActivity), progress)

        //Set Search Bar To Last Query
        mBinding.searchBar.setText(SharePrefUtil().getHistory(this@MainActivity))

        //Observing For Errors
        viewModel.getError().observe(this,
            Observer<String> {
                Toasty.error(this@MainActivity, getString(R.string.query_failed), Toast.LENGTH_LONG)
                    .show()
            })

        //Observing For Search Results
        viewModel.getSearchResults().observe(
            this,
            Observer<List<SearchModel>> { model ->
                mBinding.fab.setImageResource(R.drawable.ic_baseline_sort_24)
                resultList = model
                resultList2 = model
                mBinding.searchList.adapter = SearchAdapter(resultList)
            }
        )

    }

    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            val query = mBinding.searchBar.text.toString().trim()

            //Check If Query Is Not Empty
            if (query.isNotEmpty()) {

                //Animate EditText View
                YoYo.with(Techniques.RubberBand).duration(500).playOn(v)

                //Start Search
                viewModel.queryWord(query, mBinding.progress)

                //Set History To SharePreference
                SharePrefUtil().setHistory(this@MainActivity, query)

                //Hide Soft Input When Search Begins
                val imm = (v!!.context
                    .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                imm.hideSoftInputFromWindow(v.windowToken, 0)

            } else {

                //Show Error Toast
                Toasty.error(this, R.string.invalid).show()

                //Animate EditText View
                YoYo.with(Techniques.Shake).duration(500).playOn(v)
            }
        }
        return true;
    }

    fun SortList(view: View) {
        if (resultList.isNotEmpty()) {
            YoYo.with(Techniques.RubberBand).duration(500).playOn(view)
            val fab: FloatingActionButton = view as FloatingActionButton

            if (!isUpvote) {
                isUpvote = true

                fab.setImageResource(R.drawable.ic_thumb_up)
                Collections.sort(resultList, CompareUtils.UpvoteComparator())
                Collections.reverse(resultList)
            } else {
                isUpvote = false
                fab.setImageResource(R.drawable.ic_thumb_down)
                Collections.sort(resultList, CompareUtils.DownvoteComparator())
                Collections.reverse(resultList)

            }
            mBinding.searchList.adapter = SearchAdapter(resultList)

        } else {
            YoYo.with(Techniques.Shake).duration(500).playOn(view)
        }

    }


}
package com.hul.brainvire.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.hul.brainvire.R
import com.hul.brainvire.repository.ListRepository
import com.hul.brainvire.ui.adapter.ListAdapter
import com.hul.brainvire.util.Resource
import com.hul.brainvire.util.ViewModelFactory
import com.hul.brainvire.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    /*private lateinit var mViewModel: ListViewModel
    private val mListRepository = ListRepository()
    private var mList = ArrayList<HashMap<String, HashMap<String, String>>>()
    private lateinit var mListAdapter: ListAdapter
    val TAG = "MainActivity"
    var keyMap = HashMap<String, HashMap<String, String>>()
    var dateKey: String = ""*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
}
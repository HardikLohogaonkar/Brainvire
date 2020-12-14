package com.hul.brainvire.ui.fragment

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.hul.brainvire.R
import com.hul.brainvire.repository.ListRepository
import com.hul.brainvire.ui.adapter.ListAdapter
import com.hul.brainvire.util.Resource
import com.hul.brainvire.util.ViewModelFactory
import com.hul.brainvire.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_list.view.*


class ListFragment : Fragment() {

    private lateinit var mViewModel: ListViewModel
    private val mListRepository = ListRepository()
    private var mList = ArrayList<HashMap<String, HashMap<String, String>>>()
    private lateinit var mListAdapter: ListAdapter
    val TAG = "MainActivity"
    var keyMap = HashMap<String, HashMap<String, String>>()
    var dateKey: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_list, container, false)

        mViewModel =
            ViewModelProvider(this, ViewModelFactory(mListRepository))[ListViewModel::class.java]

        if (checkInternetConnection()) {

            mViewModel.getData()

            mViewModel.mData.observe(viewLifecycleOwner, { response ->

                when (response) {
                    is Resource.Success -> {

                        var jsonObject: JsonObject =
                            Gson().fromJson(
                                response.data!!.rates.toString(),
                                JsonObject::class.java
                            )
                        jsonObject.entrySet().forEach { it ->
                            dateKey = it.key
                            val jsonElement = it.value

                            val dateData = HashMap<String, String>()
                            keyMap[dateKey] = dateData
                            val jsonObject1: JsonObject = jsonElement.asJsonObject
                            jsonObject1.entrySet().forEach {
                                val currency = it.key
                                val values: String = it.value.toString()

                                dateData[currency] = values
                            }
                            mList.add(keyMap)
                        }

                        mListAdapter = ListAdapter(mList)
                        rvHistory.adapter = mListAdapter
                    }
                    is Resource.Error -> {

                        Toast.makeText(activity, "Somthing went wrong", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        } else {

            view.tvNoInternet.visibility = View.VISIBLE
        }
        return view
    }

    fun checkInternetConnection(): Boolean {
        //Check if connected to internet, output accordingly
        val cm =
            requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting
    }
}
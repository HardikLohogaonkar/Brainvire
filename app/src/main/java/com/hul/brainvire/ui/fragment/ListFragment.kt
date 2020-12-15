package com.hul.brainvire.ui.fragment

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.hul.brainvire.R
import com.hul.brainvire.model.Exchange
import com.hul.brainvire.model.ExchangeCurrency
import com.hul.brainvire.repository.ListRepository
import com.hul.brainvire.ui.adapter.ListAdapter
import com.hul.brainvire.util.Resource
import com.hul.brainvire.util.ViewModelFactory
import com.hul.brainvire.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_list.view.*
import org.json.JSONObject


class ListFragment : Fragment() {

    private lateinit var mViewModel: ListViewModel
    private val mListRepository = ListRepository()
    private var mList = ArrayList<Exchange>()
    private lateinit var mListAdapter: ListAdapter
    val TAG = "MainActivity"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_list, container, false)

        mViewModel =
            ViewModelProvider(this, ViewModelFactory(mListRepository))[ListViewModel::class.java]

        if (checkInternetConnection()) {

            mViewModel.getData("2018-01-01","2018-02-01","USD")

            mViewModel.mData.observe(viewLifecycleOwner, { response ->

                when (response) {
                    is Resource.Success -> {

                        try {

                            Log.d(TAG, "Response: ${response.data!!.rates}")
                            val jsonObject = JSONObject(response.data.rates.toString())

                            if (jsonObject != null) {

                                if (jsonObject.keys().hasNext()) {

                                    val exchangeDatesList = ArrayList<Exchange>()
                                    val iterator: Iterator<String> = jsonObject.keys()
                                    while (iterator.hasNext()) {
                                        val dates = Exchange()

                                        var dateKey = iterator.next()
                                        val dateObject = jsonObject.getJSONObject(dateKey)
                                        val dateIterator: Iterator<String> = dateObject.keys()

                                        dates.date = dateKey
                                        val exchangeCurrencyList = ArrayList<ExchangeCurrency>()
                                        while (dateIterator.hasNext()) {

                                            val currencyKey = dateIterator.next()
                                            val exchangeValue = dateObject.get(currencyKey)
                                            val exchangeCurrency = ExchangeCurrency()
                                            exchangeCurrency.exchangeCurrency = currencyKey
                                            exchangeCurrency.exchangeValue = exchangeValue as Double

                                            exchangeCurrencyList.add(exchangeCurrency)
                                        }
                                        dates.exchangeCurrencyList = exchangeCurrencyList
                                        exchangeDatesList.add(dates)

                                        mListAdapter =
                                            ListAdapter(requireActivity(), exchangeDatesList)
                                        rvHistory.adapter = mListAdapter
                                    }
                                }
                            }
                        } catch (e: Exception) {
                            Log.e("Error", "Msg: ${e.message}")
                        }
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
package com.hul.brainvire.ui.fragment

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.hul.brainvire.databinding.FragmentListBinding
import com.hul.brainvire.model.Exchange
import com.hul.brainvire.model.ExchangeCurrency
import com.hul.brainvire.model.GetHistoryData
import com.hul.brainvire.repository.ListRepository
import com.hul.brainvire.ui.adapter.ListAdapter
import com.hul.brainvire.util.Resource
import com.hul.brainvire.util.ViewModelFactory
import com.hul.brainvire.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : Fragment() {

    private lateinit var mViewModel: ListViewModel
    private val mListRepository = ListRepository()
    private lateinit var mListAdapter: ListAdapter
    val TAG = "MainActivity"
    private lateinit var mBinding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = FragmentListBinding.inflate(inflater, container, false)

        mBinding.lifecycleOwner = this

        mViewModel =
            ViewModelProvider(this, ViewModelFactory(mListRepository))[ListViewModel::class.java]

        if (checkInternetConnection()) {

            mViewModel.getData("2018-01-01", "2018-02-01", "USD")

            mViewModel.mData.observe(viewLifecycleOwner, getCurrencyObservable)
        } else {

            mBinding.tvNoInternet.visibility = View.VISIBLE
        }
        return mBinding.root
    }

    private fun fetchData(response: Resource<GetHistoryData>) {

        try {

            Log.d(TAG, "Response: ${response.data!!.rates}")

            val jsonObject = Gson().fromJson(
                response.data.rates.toString(),
                JsonObject::class.java
            )
            hideProgressBar()

            jsonObject?.keySet()?.forEach { _ ->
                val exchangeDatesList = ArrayList<Exchange>()
                val iterator: Iterator<String> = jsonObject.keySet().iterator()
                while (iterator.hasNext()) {
                    val dates = Exchange()
                    val dateKey = iterator.next()
                    dates.date = dateKey
                    val currencyObject = jsonObject.getAsJsonObject(dateKey)
                    val dateIterator: Iterator<String> =
                        currencyObject.keySet().iterator()
                    val exchangeCurrencyList = ArrayList<ExchangeCurrency>()
                    while (dateIterator.hasNext()) {

                        val currencyKey = dateIterator.next()
                        val exchangeValue = currencyObject.get(currencyKey)
                        val exchangeCurrency = ExchangeCurrency()
                        exchangeCurrency.exchangeCurrency = currencyKey
                        exchangeCurrency.exchangeValue =
                            exchangeValue.toString().toDouble()

                        exchangeCurrencyList.add(exchangeCurrency)
                    }
                    dates.exchangeCurrencyList = exchangeCurrencyList
                    exchangeDatesList.add(dates)

                    mListAdapter =
                        ListAdapter(requireActivity(), exchangeDatesList)
                    rvHistory.adapter = mListAdapter
                }
            }
        } catch (e: Exception) {
            Log.e("Error", "Msg: ${e.message}")
        }
    }

    private fun hideProgressBar() {
        mBinding.progressBar.visibility = View.GONE
    }

    private fun showProgressBar() {
        mBinding.progressBar.visibility = View.VISIBLE
    }

    fun checkInternetConnection(): Boolean {
        //Check if connected to internet, output accordingly
        val cm =
            requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting
    }

    private val getCurrencyObservable = Observer<Resource<GetHistoryData>> { response ->

        when (response) {
            is Resource.Loading -> {

                showProgressBar()
            }

            is Resource.Success -> {
                showProgressBar()
                fetchData(response)
            }
            is Resource.Error -> {

                hideProgressBar()
                mBinding.tvNoInternet.text = "Error while fetching data"
                mBinding.tvNoInternet.visibility = View.VISIBLE
            }
        }
    }

}
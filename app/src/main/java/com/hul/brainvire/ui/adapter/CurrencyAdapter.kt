package com.hul.brainvire.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hul.brainvire.databinding.ItemExhangeBinding
import com.hul.brainvire.model.ExchangeCurrency

class CurrencyAdapter(val mContext: Context, val exchangeArrayList: ArrayList<ExchangeCurrency>) :
    RecyclerView.Adapter<CurrencyAdapter.ViewHolderCurrency>() {

    class ViewHolderCurrency(val itemExchangeBinding: ItemExhangeBinding) :
        RecyclerView.ViewHolder(itemExchangeBinding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCurrency {

        val itemExhangeBinding =
            ItemExhangeBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolderCurrency(itemExhangeBinding)

    }

    override fun onBindViewHolder(holder: ViewHolderCurrency, position: Int) {

        var mData = exchangeArrayList[position]
        holder.itemExchangeBinding.exchangeCurrency = mData

    }

    override fun getItemCount() = exchangeArrayList.size
}
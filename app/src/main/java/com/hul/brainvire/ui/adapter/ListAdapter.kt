package com.hul.brainvire.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hul.brainvire.databinding.ItemListBinding
import com.hul.brainvire.model.ExchangeCurrency
import com.hul.brainvire.util.dateFormatConversion
import kotlinx.android.synthetic.main.item_list.view.*


class ListAdapter(
    private val mContext: Context,
    private val mList: ArrayList<String>,
    private val mCurrencyData:ArrayList<ExchangeCurrency>
) : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {

        val itemListBinding =
            ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ListViewHolder(itemListBinding)

    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

        val data = mList[position]

        dateFormatConversion(holder.itemView.tvDate, data)
        setGameDetailsRecyclerView(mContext, holder.itemListBinding.rvExchange, mCurrencyData)

    }

    override fun getItemCount() = mList.size

    class ListViewHolder(val itemListBinding: ItemListBinding) :
        RecyclerView.ViewHolder(itemListBinding.root) {

    }

    private fun setGameDetailsRecyclerView(
        context: Context,
        recyclerView: RecyclerView,
        exchangeCurrencyList: ArrayList<ExchangeCurrency>
    ) {
        val subListAdapter = CurrencyAdapter(context, exchangeCurrencyList)
        recyclerView.adapter = subListAdapter
    }
}
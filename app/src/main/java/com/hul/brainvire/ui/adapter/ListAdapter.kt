package com.hul.brainvire.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hul.brainvire.databinding.ItemListBinding
import kotlinx.android.synthetic.main.item_list.view.*


class ListAdapter(
    private val mList: ArrayList<HashMap<String, HashMap<String, String>>>,
    ) :
    RecyclerView.Adapter<ListAdapter.ListViewHolder>() {
    var value: String = ""
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {

        val itemListBinding =
            ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ListViewHolder(itemListBinding)

    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

        val data = mList[position]

        data.forEach {
            var date1 = data.keys
            Log.d("Hardik", "Currency: $it")
            holder.itemView.tvDate.text = date1.elementAt(position)
            data.forEach { it ->
                var currency = data[it.key]

                holder.itemView.tvCurrency.text = currency!!.entries.elementAt(position).toString()

            }
        }
    }

    override fun getItemCount() = mList.size

    class ListViewHolder(val itemListBinding: ItemListBinding) :
        RecyclerView.ViewHolder(itemListBinding.root)
}
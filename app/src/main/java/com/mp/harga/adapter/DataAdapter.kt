package com.mp.harga.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mp.harga.core.data.source.remote.response.CommodityResponse
import com.mp.harga.core.data.source.remote.response.ValueResponse
import com.mp.harga.databinding.ItemDataBinding
import com.mp.harga.utils.preference.DataPreference.indexClassification
import com.mp.harga.utils.preference.DataPreference.indexLocation

class DataAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items = mutableListOf<ValueResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ContactViewHolder(ItemDataBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = items[position]
        if (holder is ContactViewHolder) {
            holder.bind(model)
        }
    }

    override fun getItemCount() = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(data: CommodityResponse) {
        items.clear()
        when(indexClassification) {
            "Beras"         -> items.addAll(filterLocation(data.beras!!))
            "Daging Ayam"   -> items.addAll(filterLocation(data.dagingAyam!!))
            "Daging Sapi"   -> items.addAll(filterLocation(data.dagingSapi!!))
            "Telur Ayam"    -> items.addAll(filterLocation(data.telurAyam!!))
            "Minyak Goreng" -> items.addAll(filterLocation(data.minyakGoreng!!))
            "Gula Pasir"    -> items.addAll(filterLocation(data.gulaPasir!!))
            "Bawang Putih"  -> items.addAll(filterLocation(data.bawangPutih!!))
            "Bawang Merah"  -> items.addAll(filterLocation(data.bawangMerah!!))
            "Cabai Merah"   -> items.addAll(filterLocation(data.cabaiMerah!!))
            "Cabai Rawit"   -> items.addAll(filterLocation(data.cabaiRawit!!))
        }
        notifyDataSetChanged()
    }

    private fun filterLocation(data: List<ValueResponse>): List<ValueResponse> {
        val result = mutableListOf<ValueResponse>()
        for (item in data.indices) {
            when(data[item].name!!.lowercase()) {
                indexLocation.lowercase() -> {
                    result.add(data[item])
                    break
                }
            }
        }
        if (result.isEmpty()) {
            result.addAll(data)
        }
        return result
    }

    inner class ContactViewHolder(private val binding: ItemDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: ValueResponse) {
            with(binding) {
                tvProvince.text = model.name
                tvHarga.text = model.display
            }
        }
    }
}
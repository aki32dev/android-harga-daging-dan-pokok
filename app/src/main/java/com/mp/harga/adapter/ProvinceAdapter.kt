package com.mp.harga.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mp.harga.core.domain.model.Province
import com.mp.harga.databinding.ItemProvinceBinding

class ProvinceAdapter(
    private val onItemClick : ((String) -> Unit)? = null
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items = mutableListOf<Province>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ContactViewHolder(ItemProvinceBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = items[position]
        if (holder is ContactViewHolder) {
            holder.bind(model)
        }
    }

    override fun getItemCount() = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(data: List<Province>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    inner class ContactViewHolder(private val binding: ItemProvinceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: Province) {
            with(binding) {
                tvProvince.text = model.nameProvince
                root.setOnClickListener {
                    onItemClick?.invoke(model.nameProvince)
                }
            }
        }
    }
}
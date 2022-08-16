package com.mp.harga.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mp.harga.R
import com.mp.harga.core.domain.model.Classification
import com.mp.harga.databinding.ItemClassificationBinding
import com.mp.harga.utils.preference.DataPreference.indexClassification

class ClassificationAdapter(
    private val onItemClick : ((String) -> Unit)? = null
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items = mutableListOf<Classification>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ContactViewHolder(ItemClassificationBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = items[position]
        if (holder is ContactViewHolder) {
            holder.bind(model)
        }
    }

    override fun getItemCount() = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(data: List<Classification>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    inner class ContactViewHolder(private val binding: ItemClassificationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: Classification) {
            with(binding) {
                tvNama.text = model.name
                ivData.setImageResource(model.image)

                if (model.name == indexClassification) {
                    tvNama.setTextColor(itemView.context.resources.getColor(R.color.white, itemView.context.theme))
                    cvClassification.setCardBackgroundColor(itemView.context.resources.getColor(R.color.green_400, itemView.context.theme))
                } else {
                    tvNama.setTextColor(itemView.context.resources.getColor(R.color.grey_900, itemView.context.theme))
                    cvClassification.setCardBackgroundColor(itemView.context.resources.getColor(R.color.white, itemView.context.theme))
                }

                root.setOnClickListener {
                    onItemClick?.invoke(model.name)
                }
            }
        }
    }
}
package com.mp.harga.ui.main.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mp.harga.adapter.ProvinceAdapter
import com.mp.harga.core.domain.model.Province
import com.mp.harga.databinding.BottomSheetLocationBinding
import com.mp.harga.ui.main.SharedMainViewModel

class DialogProvince : BottomSheetDialogFragment(){
    private var _binding : BottomSheetLocationBinding?  = null
    private val binding get() = _binding!!
    private lateinit var provinceAdapter : ProvinceAdapter
    private lateinit var sharedMainViewModel : SharedMainViewModel

    companion object {
        const val TAG = "DialogProvince"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetLocationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedMainViewModel = ViewModelProvider(requireActivity())[SharedMainViewModel::class.java]
        initRecyclerView()
        setProvince()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initRecyclerView(){
        provinceAdapter = ProvinceAdapter(
            onItemClick = { province ->
                sharedMainViewModel.setDataProvince(province)
                dismiss()
            }
        )

        with(binding) {
            rvProvince.adapter = provinceAdapter
        }
    }

    private fun setProvince() {
        val province = listOf(
            Province("Semua"),
            Province("Aceh"),
            Province("Sumatera Utara"),
            Province("Sumatera Barat"),
            Province("Riau"),
            Province("Kepulauan Riau"),
            Province("Jambi"),
            Province("Bengkulu"),
            Province("Sumatera Selatan"),
            Province("Kepulauan Bangka Belitung"),
            Province("Lampung"),
            Province("Banten"),
            Province("Jawa Barat"),
            Province("DKI Jakarta"),
            Province("Jawa Tengah"),
            Province("DI Yogyakarta"),
            Province("Jawa Timur"),
            Province("Bali"),
            Province("Nusa Tenggara Barat"),
            Province("Nusa Tenggara Timur"),
            Province("Kalimantan Barat"),
            Province("Kalimantan Selatan"),
            Province("Kalimantan Tengah"),
            Province("Kalimantan Timur"),
            Province("Kalimantan Utara"),
            Province("Gorontalo"),
            Province("Sulawesi Selatan"),
            Province("Sulawesi Tenggara"),
            Province("Sulawesi Tengah"),
            Province("Sulawesi Utara"),
            Province("Sulawesi Barat"),
            Province("Maluku"),
            Province("Maluku Utara"),
            Province("Papua"),
            Province("Papua Barat")
        )
        provinceAdapter.submitList(province)
    }
}
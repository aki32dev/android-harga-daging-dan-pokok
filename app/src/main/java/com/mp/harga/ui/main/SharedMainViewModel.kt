package com.mp.harga.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedMainViewModel: ViewModel() {
    val dataProvince = MutableLiveData<String>()
    fun setDataProvince(parValue : String) { dataProvince.value = parValue }
}
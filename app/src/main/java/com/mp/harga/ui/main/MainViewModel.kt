package com.mp.harga.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mp.harga.core.data.resource.Status
import com.mp.harga.core.data.source.remote.response.DataResponse
import com.mp.harga.core.domain.usecase.main.MainUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(
    private val mainUseCase: MainUseCase
): ViewModel() {
    val errorLiveData = MutableLiveData<Boolean>()
    val loadingLiveData = MutableLiveData<Boolean>()
    val recipesLiveData = MutableLiveData<DataResponse>()

    fun getData(){
        viewModelScope.launch {
            mainUseCase.getData().collect { response ->
                when(response){
                    is Status.Loading -> {
                        loadingLiveData.value = true
                    }
                    is Status.Success -> {
                        errorLiveData.value = false
                        loadingLiveData.value = false
                        recipesLiveData.value = response.data!!
                    }
                    is Status.Error -> {
                        errorLiveData.value = true
                        loadingLiveData.value = false
                    }
                    is Status.HttpError -> {
                        errorLiveData.value = true
                        loadingLiveData.value = false
                    }
                }
            }
        }
    }
}
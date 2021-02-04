package com.androdevlinux.coinfeed.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androdevlinux.coinfeed.model.Ticker
import com.androdevlinux.coinfeed.network.LiveDataWrapper
import com.androdevlinux.coinfeed.repository.TickerRepository
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class MainViewModel : ViewModel(), KoinComponent {
    var tickerResponse = MutableLiveData<LiveDataWrapper<Ticker>>()
    val mLoadingLiveData = MutableLiveData<Boolean>()
    private val job = SupervisorJob()
    private val tickerRepository : TickerRepository by inject()


    fun getTickerData() {
        if(tickerResponse.value == null){
            viewModelScope.launch {
                tickerResponse.value = LiveDataWrapper.loading()
                setLoadingVisibility(true)
                try {
                    val data = viewModelScope.async {
                        return@async tickerRepository.getTickerData()
                    }.await()
                    try {
                        tickerResponse.value = LiveDataWrapper.success(data)
                    } catch (e: Exception) {
                    }
                    setLoadingVisibility(false)
                } catch (e: Exception) {
                    setLoadingVisibility(false)
                    tickerResponse.value = LiveDataWrapper.error(e)
                }
            }
        }
    }

    private fun setLoadingVisibility(visible: Boolean) {
        mLoadingLiveData.postValue(visible)
    }

    override fun onCleared() {
        super.onCleared()
        this.job.cancel()
    }
}
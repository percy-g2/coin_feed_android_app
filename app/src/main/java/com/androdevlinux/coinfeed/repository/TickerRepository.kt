package com.androdevlinux.coinfeed.repository

import com.androdevlinux.coinfeed.model.Ticker
import com.androdevlinux.coinfeed.network.TickerAPIService
import org.koin.core.KoinComponent
import org.koin.core.inject

class TickerRepository : KoinComponent {
    private val mTickerAPIService: TickerAPIService by inject()

    /**
     * Request data from backend
     */
    suspend fun getTickerData(): Ticker {
        return processDataFetchLogic()
    }

    private suspend fun processDataFetchLogic(): Ticker{
        return mTickerAPIService.getTickerData()
    }
}
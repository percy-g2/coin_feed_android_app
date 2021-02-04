package com.androdevlinux.coinfeed.network

import com.androdevlinux.coinfeed.model.Ticker
import retrofit2.http.GET

interface TickerAPIService {
    @GET("crypto-premiums-arbitrage-in-india/ticker.json")
    suspend fun getTickerData(): Ticker
}
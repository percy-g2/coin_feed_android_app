package com.androdevlinux.coinfeed.model


import com.google.gson.annotations.SerializedName

data class TickerItem(@SerializedName("exchange_name")
                      val exchangeName: String = "",
                      @SerializedName("at")
                      val at: String = "",
                      @SerializedName("price")
                      val price: Double = 0.0,
                      @SerializedName("exchange_symbol")
                      val exchangeSymbol: String = "")


data class MATIC(@SerializedName("ticker")
                 val ticker: List<TickerItem> = emptyList(),
                 @SerializedName("buy")
                 val buy: List<BuyItem> = emptyList(),
                 @SerializedName("sell")
                 val sell: List<SellItem> = emptyList())


data class BCH(@SerializedName("ticker")
               val ticker: List<TickerItem> = emptyList(),
               @SerializedName("buy")
               val buy: List<BuyItem> = emptyList(),
               @SerializedName("sell")
               val sell: List<SellItem> = emptyList())


data class USDT(@SerializedName("ticker")
                val ticker: List<TickerItem> = emptyList(),
                @SerializedName("buy")
                val buy: List<BuyItem> = emptyList(),
                @SerializedName("sell")
                val sell: List<SellItem> = emptyList())


data class BTC(@SerializedName("ticker")
               val ticker: List<TickerItem> = emptyList(),
               @SerializedName("buy")
               val buy: List<BuyItem> = emptyList(),
               @SerializedName("sell")
               val sell: List<SellItem> = emptyList())


data class SellItem(@SerializedName("exchange_name")
                    val exchangeName: String = "",
                    @SerializedName("at")
                    val at: String = "",
                    @SerializedName("price")
                    val price: Double = 0.0,
                    @SerializedName("exchange_symbol")
                    val exchangeSymbol: String = "")


data class Ticker(@SerializedName("BTC")
                val btc: BTC,
                @SerializedName("MATIC")
                val matic: MATIC,
                @SerializedName("BCH")
                val bch: BCH,
                @SerializedName("XRP")
                val xrp: XRP,
                @SerializedName("BNB")
                val bnb: BNB,
                @SerializedName("ETH")
                val eth: ETH,
                @SerializedName("USDT")
                val usdt: USDT,
                @SerializedName("LTC")
                val ltc: LTC,
                @SerializedName("TRX")
                val trx: TRX)


data class BuyItem(@SerializedName("exchange_name")
                   val exchangeName: String = "",
                   @SerializedName("at")
                   val at: String = "",
                   @SerializedName("price")
                   val price: Double = 0.0,
                   @SerializedName("exchange_symbol")
                   val exchangeSymbol: String = "")


data class XRP(@SerializedName("ticker")
               val ticker: List<TickerItem> = emptyList(),
               @SerializedName("buy")
               val buy: List<BuyItem> = emptyList(),
               @SerializedName("sell")
               val sell: List<SellItem> = emptyList())


data class BNB(@SerializedName("ticker")
               val ticker: List<TickerItem> = emptyList(),
               @SerializedName("buy")
               val buy: List<BuyItem> = emptyList(),
               @SerializedName("sell")
               val sell: List<SellItem> = emptyList())


data class ETH(@SerializedName("ticker")
               val ticker: List<TickerItem> = emptyList(),
               @SerializedName("buy")
               val buy: List<BuyItem> = emptyList(),
               @SerializedName("sell")
               val sell: List<SellItem> = emptyList())


data class LTC(@SerializedName("ticker")
               val ticker: List<TickerItem> = emptyList(),
               @SerializedName("buy")
               val buy: List<BuyItem> = emptyList(),
               @SerializedName("sell")
               val sell: List<SellItem> = emptyList())


data class TRX(@SerializedName("ticker")
               val ticker: List<TickerItem> = emptyList(),
               @SerializedName("buy")
               val buy: List<BuyItem> = emptyList(),
               @SerializedName("sell")
               val sell: List<SellItem> = emptyList())



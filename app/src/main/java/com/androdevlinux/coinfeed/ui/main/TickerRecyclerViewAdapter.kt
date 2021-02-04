package com.androdevlinux.coinfeed.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androdevlinux.coinfeed.R
import com.androdevlinux.coinfeed.databinding.TickerAdapterRowBinding
import com.androdevlinux.coinfeed.model.Ticker
import com.androdevlinux.coinfeed.utils.TimeUtil

class TickerRecyclerViewAdapter(private val context: Context, private var ticker: Ticker):
    RecyclerView.Adapter<TickerRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TickerAdapterRowBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.itemView.clearAnimation()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(ticker.btc.ticker[position]) {
                binding.exchangeName.text = exchangeName
                binding.price.text = context.getString(R.string.rupee_symbol).plus(" ").plus(price.toString())
                if (this.exchangeSymbol != "WAZIRX" &&  exchangeSymbol != "COINDCX") {
                    TimeUtil.getMinWithMicroSecs(at).also {
                        if (it > 0) {
                            binding.time.text = it.toString().plus(" min ago")
                        } else {
                            binding.time.text = context.getString(R.string.few_seconds_ago)
                        }
                    }
                } else {
                    TimeUtil.getMinWithMilliSecs(at).also {
                        if (it > 0) {
                            binding.time.text = it.toString().plus(" min ago")
                        } else {
                            binding.time.text = context.getString(R.string.few_seconds_ago)
                        }
                    }
                }
            }
        }
    }

    /**
     * View holder class
     */
    inner class ViewHolder(val binding: TickerAdapterRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int {
        return ticker.btc.ticker.size
    }

    fun updateData(ticker: Ticker) {
        this.ticker = ticker
        notifyDataSetChanged()
    }
}
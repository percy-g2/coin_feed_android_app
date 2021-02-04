package com.androdevlinux.coinfeed.ui.main

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.androdevlinux.coinfeed.databinding.MainFragmentBinding
import com.androdevlinux.coinfeed.model.*
import com.androdevlinux.coinfeed.network.LiveDataWrapper

class MainFragment : Fragment() {

    private lateinit var tickerRecyclerViewAdapter: TickerRecyclerViewAdapter
    private lateinit var _binding: MainFragmentBinding
    private lateinit var ticker: Ticker
    private val binding get() = _binding

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.tickerResponse.observe(viewLifecycleOwner, mDataObserver)
        viewModel.mLoadingLiveData.observe(viewLifecycleOwner, loadingObserver)

        ticker = Ticker(BTC(), MATIC(), BCH(), XRP(), BNB(), ETH(), USDT(), LTC(), TRX())
        tickerRecyclerViewAdapter = TickerRecyclerViewAdapter(requireContext(), ticker)
        binding.listRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.listRecyclerView.adapter = tickerRecyclerViewAdapter

        viewModel.getTickerData()
    }

    private val mDataObserver = Observer<LiveDataWrapper<Ticker>> { result ->
        when (result?.responseStatus) {
            LiveDataWrapper.RESPONSESTATUS.LOADING -> {
                // Loading data
            }
            LiveDataWrapper.RESPONSESTATUS.ERROR -> {
                // Error for http request
                Log.d("mDataObserver", "LiveDataResult.Status.ERROR = ${result.response}")

            }
            LiveDataWrapper.RESPONSESTATUS.SUCCESS -> {
                // Data from API
                Log.d("mDataObserver", "LiveDataResult.Status.SUCCESS = ${result.response}")
                result.response?.let {
                    processData(it)
                }
            }
        }
    }

    /**
     * Handle success data
     */
    private fun processData(ticker: Ticker) {
        val refresh = Handler(Looper.getMainLooper())
        refresh.post {
            tickerRecyclerViewAdapter.updateData(ticker)
        }
    }

    /**
     * Hide / show loader
     */
    private val loadingObserver = Observer<Boolean> { visible ->
        // Show/hide progress bar
        if (visible) {
            _binding.progressBar.visibility = View.VISIBLE
        } else {
            _binding.progressBar.visibility = View.INVISIBLE
        }
    }
}
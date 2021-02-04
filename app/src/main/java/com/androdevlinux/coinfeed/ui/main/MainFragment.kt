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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.androdevlinux.coinfeed.databinding.MainFragmentBinding
import com.androdevlinux.coinfeed.model.*
import com.androdevlinux.coinfeed.network.LiveDataWrapper

class MainFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

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
        _binding.listRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        _binding.listRecyclerView.adapter = tickerRecyclerViewAdapter
        _binding.swipeLayout.setOnRefreshListener(this)
        viewModel.getTickerData()
    }

    private val mDataObserver = Observer<LiveDataWrapper<Ticker>> { result ->
        when (result?.responseStatus) {
            LiveDataWrapper.RESPONSESTATUS.LOADING -> {
                // Loading data
            }
            LiveDataWrapper.RESPONSESTATUS.ERROR -> {
                // Error for https request
                _binding.errorHolder.visibility = View.VISIBLE
                Log.d("mDataObserver", "LiveDataResult.Status.ERROR = ${result.error}")

            }
            LiveDataWrapper.RESPONSESTATUS.SUCCESS -> {
                // Data from API
                _binding.errorHolder.visibility = View.GONE
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
            if (_binding.swipeLayout.isRefreshing) {
                _binding.swipeLayout.isRefreshing = false
            }
            _binding.progressBar.visibility = View.INVISIBLE
        }
    }

    override fun onRefresh() {
        viewModel.getTickerData()
    }
}
package com.androdevlinux.coinfeed.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.androdevlinux.coinfeed.databinding.MainFragmentBinding
import com.androdevlinux.coinfeed.model.Ticker
import com.androdevlinux.coinfeed.network.LiveDataWrapper

class MainFragment : Fragment() {

    private lateinit var _binding: MainFragmentBinding
    private val binding get() = _binding

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.tickerResponse.observe(viewLifecycleOwner, mDataObserver)
        viewModel.mLoadingLiveData.observe(viewLifecycleOwner, loadingObserver)
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
            }
        }
    }

    /**
     * Hide / show loader
     */
    private val loadingObserver = Observer<Boolean> { visible ->
        // Show/hide progress bar
        if(visible){
            _binding.progressBar.visibility = View.VISIBLE
        }else{
            _binding.progressBar.visibility = View.INVISIBLE
        }
    }
}
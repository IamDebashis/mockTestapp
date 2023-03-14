package com.example.mocktestapp.presentation.ui.live_test

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.mocktestapp.R
import com.example.mocktestapp.databinding.FragmentLiveTestBinding
import com.example.mocktestapp.presentation.ui.live_test.adapter.LiveTestAdapter
import com.example.mocktestapp.util.observeFlow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LiveTestFragment : Fragment() {

    private var _binding: FragmentLiveTestBinding? = null
    private val binding get() = _binding!!


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    private val adapter by lazy { LiveTestAdapter() }
    private val viewModel: LiveTestViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLiveTestBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            rvLiveTest.adapter = adapter
        }

        init()

    }

    fun init() {
        observeFlow(viewModel.listTest) {
            it?.let { testList ->
                adapter.submitList(testList)
            }
        }
    }


}
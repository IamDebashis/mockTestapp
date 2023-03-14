package com.example.mocktestapp.presentation.ui.test_package

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.mocktestapp.R
import com.example.mocktestapp.databinding.FragmentTestPackageBinding
import com.example.mocktestapp.presentation.ui.test_package.adapter.TestPackageAdapter
import com.example.mocktestapp.util.observeFlow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestPackageFragment : Fragment() {

    private var _binding: FragmentTestPackageBinding? = null
    private val binding get() = _binding!!


    private val viewModel: TestPackgeViewModel by viewModels()

    private val adapter by lazy { TestPackageAdapter() }


    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentTestPackageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            rvTestPackage.adapter = adapter
        }

        init()
    }

    fun init() {
        observeFlow(viewModel.testPackage) {
            it?.let { testList ->
                adapter.submitList(testList)
            }
        }
    }

}
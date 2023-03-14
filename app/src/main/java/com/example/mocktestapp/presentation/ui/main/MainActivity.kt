package com.example.mocktestapp.presentation.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.example.mocktestapp.databinding.ActivityMainBinding
import com.example.mocktestapp.presentation.ui.core.ViewPagerAdapter
import com.example.mocktestapp.presentation.ui.live_test.LiveTestFragment
import com.example.mocktestapp.presentation.ui.test_package.TestPackageFragment
import com.example.mocktestapp.presentation.ui.util.UiState
import com.example.mocktestapp.util.observeFlow
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val fragmentList = mutableListOf(
        "Test Package" to TestPackageFragment(),
        "Live Test" to LiveTestFragment()
    )

    private val viewModel: MainViweModel by viewModels()


    private val adapter by lazy { ViewPagerAdapter(this, fragmentList.map { it.second }) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = fragmentList[position].first
        }.attach()

        init()

    }

    fun init() {
        observeFlow(viewModel.uiState) { state ->
            when (state) {
                UiState.Failed -> {
                    binding.apply {
                        tabLayout.isVisible = false
                        progressBar.isVisible = false
                        viewPager.isVisible = false
                    }
                }
                UiState.Loading -> {
                    binding.apply {
                        tabLayout.isVisible = false
                        progressBar.isVisible = true
                        viewPager.isVisible = false
                    }
                }
                UiState.Success -> {
                    binding.apply {
                        tabLayout.isVisible = true
                        progressBar.isVisible = false
                        viewPager.isVisible = true
                    }
                }
            }

        }

        observeFlow(viewModel.configuration) {
            it?.let { config ->

                if (!config.showMockTests) {
                    fragmentList.removeAt(1)
                }
                if (!config.showTestPackages) {
                    fragmentList.removeAt(0)
                }

            }
        }

    }


}
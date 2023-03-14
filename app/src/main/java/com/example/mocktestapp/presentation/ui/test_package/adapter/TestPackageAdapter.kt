package com.example.mocktestapp.presentation.ui.test_package.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mocktestapp.data.module.TestPackageModule
import com.example.mocktestapp.databinding.ItemTestPackageBinding

class TestPackageAdapter :
    ListAdapter<TestPackageModule, TestPackageAdapter.TestPackageViewHolder>(diffCallback) {


    private companion object {
        val diffCallback = object : DiffUtil.ItemCallback<TestPackageModule>() {
            override fun areItemsTheSame(
                oldItem: TestPackageModule,
                newItem: TestPackageModule
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: TestPackageModule,
                newItem: TestPackageModule
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestPackageViewHolder {
        return TestPackageViewHolder(
            ItemTestPackageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TestPackageViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(it)
        }
    }

    inner class TestPackageViewHolder(private val binding: ItemTestPackageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(testPackage: TestPackageModule) {
            val progress =
                testPackage.stats.attemptCount.count * 100 / testPackage.stats.testCount.count
            binding.apply {
                tvTitle.text = testPackage.title
                tvTotalTest.text = "${testPackage.stats.testCount.count} + Tests"
                tvExpire.text = "${testPackage.expiredInDays} days"
                progressBar.progress = progress
                tvPercent.text = "$progress %"
                tvCount.text =
                    "${testPackage.stats.attemptCount.count}/${testPackage.stats.testCount.count}"
            }
        }


    }


}
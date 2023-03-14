package com.example.mocktestapp.presentation.ui.live_test.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mocktestapp.data.module.MockTestModule
import com.example.mocktestapp.databinding.ItemLiveTestBinding
import com.example.mocktestapp.databinding.ItemTestPackageBinding

class LiveTestAdapter :
    ListAdapter<MockTestModule, LiveTestAdapter.LiveTestViewHolder>(diffCallBack) {


    private companion object {
        private val diffCallBack = object : DiffUtil.ItemCallback<MockTestModule>() {
            override fun areItemsTheSame(
                oldItem: MockTestModule,
                newItem: MockTestModule
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: MockTestModule,
                newItem: MockTestModule
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LiveTestViewHolder {
        return LiveTestViewHolder(
            ItemLiveTestBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: LiveTestViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(it)
        }
    }

    inner class LiveTestViewHolder(private val binding: ItemLiveTestBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(liveTest: MockTestModule) {
            binding.apply {
                tvTitle.text = liveTest.title
                tvTotalQs.text = "${liveTest.testDetails.questionCount} QS"
                tvMark.text = "${liveTest.testDetails.totalMark} Marks"
                tvTime.text = "${liveTest.testDetails.duration / 60} min"
            }


        }


    }


}
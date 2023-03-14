package com.example.mocktestapp.data.module

data class TestDetails(
    val description: String,
    val duration: Int,
    val etag: String,
    val id: String,
    val isAvailable: Boolean,
    val isPublished: Boolean,
    val languages: List<String>,
    val publishedAt: Int,
    val questionCount: Int,
    val totalMark: Int
)
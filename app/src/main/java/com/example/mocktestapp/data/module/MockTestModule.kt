package com.example.mocktestapp.data.module

data class MockTestModule(
    val etag: String,
    val id: String,
    val sku: String,
    val slug: String,
    val testDetails: TestDetails,
    val title: String,
    val validityExpiredAt: Int
)
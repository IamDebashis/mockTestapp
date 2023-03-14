package com.example.mocktestapp.data.module

data class TestPackageModule(
    val bundelId: String,
    val etag: String,
    val expiredInDays: Int,
    val features: List<String>,
    val id: String,
    val sku: String,
    val slug: String,
    val stats: StatsX,
    val title: String
)
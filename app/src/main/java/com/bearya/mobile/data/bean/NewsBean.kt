package com.bearya.mobile.data.bean

import com.bearya.mobile.data.entity.News

data class NewsBean(
    val count: Int,
    val limit: Int,
    val list: List<News>,
    val pos: Int,
    val title: String,
    val total: Int
)
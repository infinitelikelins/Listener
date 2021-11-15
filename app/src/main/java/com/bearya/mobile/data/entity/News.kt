package com.bearya.mobile.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class News(
    @PrimaryKey val id: Int,
    val author: String,
    val cover_path: String,
    val digest: String,
    val liked: Int,
    val likes: Int,
    val no_add: Int,
    val no_collect: Int,
    val no_play: Int,
    val publish_time: Long,
    val share_url: String,
    val title: String,
    val url: String,
    val voice_name: String,
    val voice_path: String,
    val voice_playtime_seconds: Int
)

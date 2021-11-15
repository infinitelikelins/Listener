package com.bearya.mobile.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Teachers(@PrimaryKey val id: Int,
                    val name: String,
                    val subtitle: String,
                    val group_name: String,
                    val cover_path: String,
                    val cover_url: String,
                    val group_cover_url: String,
                    val group_cover_path: String,
                    val group_id: Int,
                    val material_count: Int)

package com.bearya.mobile.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.bearya.mobile.data.converters.Converters

@Entity
@TypeConverters(Converters::class)
data class Poetry(@PrimaryKey val id: String,
                  val oneSentence: String,
                  val popularity: Long,
                  val title: String,
                  val dynasty: String,
                  val author: String,
                  val allSentence: List<String>?,
                  val matchTags: List<String>?,
                  val translate: List<String>?,
                  val date: String)

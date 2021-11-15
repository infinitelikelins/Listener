package com.bearya.mobile.data.converters

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun fromArray(value : String?) : List<String>? = value?.split("#")

    @TypeConverter
    fun toStrings(strings : List<String>?) : String? = strings?.joinToString("#")

}
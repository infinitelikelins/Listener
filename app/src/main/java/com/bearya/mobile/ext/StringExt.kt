package com.bearya.mobile.ext

fun String?.assetsPath(): String = "file:///android_asset/$this"

fun String?.hidePhoneNumber(): String? = this?.replace(Regex("(\\d{3})\\d{4}(\\d{4})"), "$1****$2")

fun String?.isNotPhoneNumber(): Boolean = isNullOrBlank() || !Regex("^1(3\\d|5[012356789]|8[0123456789]|7[0123678]|4[9]|9[012356789])\\d{8}$").matches(this)

fun String?.isNotPasswordMatches(): Boolean = isNullOrBlank() || length !in 6..20
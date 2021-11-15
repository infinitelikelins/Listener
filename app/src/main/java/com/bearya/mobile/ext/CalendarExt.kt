package com.bearya.mobile.ext

import java.util.Calendar

fun currentDate(year: String = "", month: String = "" , day :String = "") = Calendar.getInstance().run {
    "${get(Calendar.YEAR)}${year}${(get(Calendar.MONTH) + 1).let { if(it > 9) "$it" else "0$it" }}${month}${get(Calendar.DATE)}${day}"
}

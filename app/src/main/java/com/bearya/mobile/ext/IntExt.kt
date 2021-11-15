package com.bearya.mobile.ext

import android.content.Context

fun Int?.dp2px(context: Context): Float? = this?.times(context.resources.displayMetrics.density)?.plus(0.5f)

fun Int?.px2dp(context: Context) : Float? = this?.div(context.resources.displayMetrics.density)?.plus(0.5f)
package com.bearya.mobile.data.repository

import android.text.style.AbsoluteSizeSpan
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.core.text.inSpans
import androidx.core.text.underline
import androidx.core.text.italic
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

object NewsRepository {

    fun pullTheLastNews(limit: Int = 20, pos: Int = 1) {

    }

    fun test(scope: CoroutineScope, callback: (CharSequence?) -> Unit) {
        scope.launch {
            flow {
                emit(buildSpannedString())
            }.collect {
                callback.invoke(it)
            }
        }
    }

    /**
     * 为诗词组合匹配界面格式字符数据
     */
    private fun buildSpannedString() = buildSpannedString {
        bold {
            inSpans(AbsoluteSizeSpan(64)) {
                append("《噗通噗通磕头虫》")
            }
        }
        underline {
            italic {
                inSpans(AbsoluteSizeSpan(44)) {
                    append("\n\n蹭蹭什么也不想做，可是饿了怎么办呢？")
                }
            }
        }
    }

}
package com.bearya.mobile.data.repository

import android.text.style.AbsoluteSizeSpan
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.core.text.inSpans
import androidx.core.text.italic
import com.bearya.mobile.app.AppInit
import com.bearya.mobile.data.entity.Poetry
import com.bearya.mobile.ext.currentDate
import com.jinrishici.sdk.android.JinrishiciClient
import com.jinrishici.sdk.android.listener.JinrishiciCallback
import com.jinrishici.sdk.android.model.JinrishiciRuntimeException
import com.jinrishici.sdk.android.model.PoetySentence
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

object PoetryRepository {

    /**
     * 本地查询今日诗词的接口,没有查到就去网络查询
     */
    fun queryTodaySentence(scope: CoroutineScope, func: (poetry: CharSequence?) -> Unit) {
        scope.launch {
            AppInit.database.poetryDao().queryOneByDate(currentDate()).collect {
                if(it == null) {
                    todaySentence(scope)
                } else {
                    func.invoke(spannedDecorator(it))
                }
            }
        }
    }

    /**
     * 为诗词组合匹配界面格式字符数据
     */
    private fun spannedDecorator(poetry: Poetry) : CharSequence = buildSpannedString {
        bold {
            inSpans(AbsoluteSizeSpan(64)) {
                append("《${poetry.title}》")
            }
        }
        italic {
            inSpans(AbsoluteSizeSpan(44)) {
                append("\n\n——${poetry.author}(${poetry.dynasty})\n\n")
            }
        }
        inSpans(AbsoluteSizeSpan(50)) {
            append(poetry.oneSentence)
        }
    }

    /**
     * 网络查询今日诗词的接口,写入数据库
     */
    private fun todaySentence(scope: CoroutineScope) {
        JinrishiciClient.getInstance().getOneSentenceBackground(object : JinrishiciCallback {
            override fun done(poetySentence: PoetySentence?) {
                poetySentence?.data?.let {
                    Poetry(id = it.id, allSentence = it.origin.content, title = it.origin.title,
                           author = it.origin.author, dynasty = it.origin.dynasty,
                           oneSentence = it.content, popularity = it.popularity.toLong(),
                           matchTags = it.matchTags, translate = it.origin.translate,
                           date = currentDate())
                }?.run {
                    scope.launch(Dispatchers.IO) {
                        AppInit.database.poetryDao().insertOne(this@run)
                    }
                }
            }

            override fun error(e: JinrishiciRuntimeException?) {
                e?.printStackTrace()
            }
        })
    }

}
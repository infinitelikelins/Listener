package com.bearya.mobile.app

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.room.Room
import androidx.startup.Initializer
import com.bearya.mobile.dialog.AppStyle
import com.bearya.mobile.listener.BuildConfig
import com.bearya.mobile.listener.R
import com.bearya.mobile.tools.DefaultBinding
import com.jinrishici.sdk.android.factory.JinrishiciFactory
import com.kongzue.dialogx.DialogX
import com.kongzue.dialogx.util.TextInfo
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import com.tencent.mmkv.MMKV

class AppInit : Initializer<Any> {

    companion object {
        lateinit var database: AppDatabase
    }

    override fun create(context: Context) {
        val formatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(true)
            .methodCount(1)
            .tag("LOGGER")
            .build()

        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })

        MMKV.initialize(context)

        JinrishiciFactory.init(context)

        DataBindingUtil.setDefaultComponent(DefaultBinding)

        DialogX.init(context)
        DialogX.globalStyle = AppStyle()
        DialogX.globalTheme = DialogX.THEME.AUTO

        database = Room.databaseBuilder(context, AppDatabase::class.java, "listener.db")
            .createFromAsset("database/story.db")
            .build()

    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
}
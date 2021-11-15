package com.bearya.mobile.data.model

import android.app.Application
import androidx.core.text.buildSpannedString
import androidx.core.text.inSpans
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.bearya.mobile.widget.ClickableSpanText

class ForgetViewModel(application: Application) : AndroidViewModel(application) {

    var userPhoneNumber: String? = null
    var userPassword: String? = null
    var userSmsCode: String? = null

    val mIsPhoneError: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val mIsPasswordError: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val mIsSmsCodeError: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val mPhoneError: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val mPasswordError: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val mSmsCodeError: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    // 我已阅读并同意《服务协议》、《隐私协议》和《儿童个人信息保护政策》
    val protocolText = buildSpannedString {
        append("我已阅读并同意")
        inSpans(ClickableSpanText(application.applicationContext, ClickableSpanText.USER_PROTOCOL)) {
            append("《服务协议》")
        }
        append("、")
        inSpans(ClickableSpanText(application.applicationContext, ClickableSpanText.PRIVACY_PROTOCOL)) {
            append("《隐私协议》")
        }
        append("和")
        inSpans(ClickableSpanText(application.applicationContext, ClickableSpanText.CHILDREN_PRIVACY_PROTOCOL)) {
            append("《儿童个人信息保护政策》")
        }
    }

}
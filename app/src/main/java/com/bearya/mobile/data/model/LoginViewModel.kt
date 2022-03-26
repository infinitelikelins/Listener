package com.bearya.mobile.data.model

import android.app.Application
import androidx.core.text.buildSpannedString
import androidx.core.text.inSpans
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bearya.mobile.data.repository.UserRepository
import com.bearya.mobile.ext.isNotPasswordMatches
import com.bearya.mobile.ext.isNotPhoneNumber
import com.bearya.mobile.ext.setData
import com.bearya.mobile.widget.ClickableSpanText
import es.dmoral.toasty.Toasty

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    var userPhoneNumber: String? = null
    var userPassword: String? = null

    val mIsPhoneError: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val mIsPasswordError: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val mPhoneError: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val mPasswordError: MutableLiveData<String> by lazy { MutableLiveData<String>() }

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

    fun login(successFunc: () -> Unit) {
        when {
            userPhoneNumber.isNotPhoneNumber() ->
                formMessage(isPhoneError = true, phoneError = "请输入正确的手机号码")
            userPassword.isNotPasswordMatches() ->
                formMessage(isPasswordError = true, passwordError = "请输入登录密码")
            else -> {
                formMessage()
                UserRepository.userLogin(viewModelScope, userPhoneNumber!!, userPassword!!, successFunc) {
                    when {
                        it.message?.contains("帐号") == true -> formMessage(isPhoneError = true, phoneError = "${it.message}")
                        it.message?.contains("密码") == true -> formMessage(isPasswordError = true, passwordError = "${it.message}")
                        else -> Toasty.warning(getApplication<Application>().applicationContext,"服务异常,登录失败").show()
                    }
                }
            }
        }
    }

    private fun formMessage(isPhoneError: Boolean = false, isPasswordError: Boolean = false, phoneError: String = "", passwordError: String = "") {
        mIsPasswordError.setData(isPasswordError)
        mIsPhoneError.setData(isPhoneError)
        mPhoneError.setData(phoneError)
        mPasswordError.setData(passwordError)
    }

}
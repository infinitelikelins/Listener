package com.bearya.mobile.widget

import android.content.Context
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View
import androidx.core.content.ContextCompat
import com.bearya.mobile.listener.R
import es.dmoral.toasty.Toasty

class ClickableSpanText(private val context: Context, private val type: Int) : ClickableSpan() {

    companion object {
        //贝芽小作家服务协议
        const val USER_PROTOCOL = 1

        //贝芽小作家隐私政策
        const val PRIVACY_PROTOCOL = 2

        //贝芽小作家儿童隐私保护政策
        const val CHILDREN_PRIVACY_PROTOCOL = 3

        //会员服务协议
        const val VIP_MEMBER_PROTOCOL = 4

        //会员服务隐私协议
        const val VIP_MEMBER_PRIVACY_PROTOCOL = 5
    }

    override fun onClick(view: View) {
        view.cancelPendingInputEvents()
        when (type) {
            USER_PROTOCOL -> Toasty.success(context, "贝芽小作家服务协议").show()
            PRIVACY_PROTOCOL -> Toasty.success(context, "贝芽小作家隐私政策").show()
            CHILDREN_PRIVACY_PROTOCOL -> Toasty.success(context, "贝芽小作家儿童隐私保护政策").show()
            VIP_MEMBER_PROTOCOL -> Toasty.success(context, "会员服务协议").show()
            VIP_MEMBER_PRIVACY_PROTOCOL -> Toasty.success(context, "会员服务隐私协议").show()
        }
    }

    override fun updateDrawState(ds: TextPaint) {
        super.updateDrawState(ds)
        ds.color = ContextCompat.getColor(context , R.color.dust_red_4)
        ds.isUnderlineText = false
    }

}
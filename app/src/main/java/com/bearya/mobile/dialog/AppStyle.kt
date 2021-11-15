package com.bearya.mobile.dialog

import com.bearya.mobile.listener.R
import com.kongzue.dialogx.interfaces.DialogXStyle
import com.kongzue.dialogx.style.MIUIStyle

class AppStyle : MIUIStyle() {

    override fun overrideVerticalButtonRes(): DialogXStyle.VerticalButtonRes =
        object : DialogXStyle.VerticalButtonRes {
            override fun overrideVerticalOkButtonBackgroundRes(visibleButtonCount: Int, isLight: Boolean): Int =
                R.drawable.ripple_dust_red

            override fun overrideVerticalCancelButtonBackgroundRes(visibleButtonCount: Int, isLight: Boolean): Int =
                if (isLight) R.drawable.ripple_gray_4 else R.drawable.ripple_gray_12

            override fun overrideVerticalOtherButtonBackgroundRes(visibleButtonCount: Int, isLight: Boolean): Int =
                if (isLight) R.drawable.ripple_gray_4 else R.drawable.ripple_gray_12

        }

}
package com.bearya.mobile.tools

import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingComponent
import coil.load

/**
 * DataBinding 默认控件组合
 */
object DefaultBinding : DataBindingComponent {

    @JvmStatic
    @BindingAdapter(value = ["loadUrl", "error"], requireAll = false)
    fun load(imageView: ImageView, url: String?, error: Drawable?) {
        if(url != null && url.startsWith("http",true)) {
            imageView.load(url) {
                crossfade(true)
                crossfade(300)
                if(error != null) error(error)
            }
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["selected"], requireAll = false)
    fun selected(textView: TextView, status: Boolean?) {
        textView.isSelected = status ?: false
    }

}
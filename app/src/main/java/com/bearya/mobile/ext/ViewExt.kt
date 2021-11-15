package com.bearya.mobile.ext

import android.animation.Keyframe
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import coil.load
import com.bearya.mobile.listener.R

fun withShow(vararg view: View) = view.forEach { it.visibility = View.VISIBLE }

fun withGone(vararg view: View) = view.forEach { it.visibility = View.GONE }

fun View.show() {
    visibility = View.VISIBLE
}

fun ImageView.showImageResource(imageResource: Int) {
    visibility = View.VISIBLE
    setImageResource(imageResource)
}

fun ImageView.loadUrl(url: String?,
                      error: Drawable? = null,
                      placeHolder: Drawable? = null,
                      crossFadeEnable: Boolean = true,
                      crossFadeTime: Int = 100)
    = load(url) { crossfade(crossFadeEnable);crossfade(crossFadeTime);error(error);placeholder(placeHolder) }

fun View.gone() {
    visibility = View.GONE
}

fun View.startShake(scaleSmall: Float = 0.9f, scaleLarge: Float = 1.1f, shakeDegrees: Float = 10f, duration: Long = 1000) {
    val scaleXValuesHolder = PropertyValuesHolder.ofKeyframe(View.SCALE_X,
            Keyframe.ofFloat(0f, 1.0f),
            Keyframe.ofFloat(0.25f, scaleSmall),
            Keyframe.ofFloat(0.5f, scaleLarge),
            Keyframe.ofFloat(0.75f, scaleLarge),
            Keyframe.ofFloat(1.0f, 1.0f)
    )
    val scaleYValuesHolder = PropertyValuesHolder.ofKeyframe(View.SCALE_Y,
            Keyframe.ofFloat(0f, 1.0f),
            Keyframe.ofFloat(0.25f, scaleSmall),
            Keyframe.ofFloat(0.5f, scaleLarge),
            Keyframe.ofFloat(0.75f, scaleLarge),
            Keyframe.ofFloat(1.0f, 1.0f)
    )
    val rotateValuesHolder = PropertyValuesHolder.ofKeyframe(View.ROTATION,
            Keyframe.ofFloat(0f, 0f),
            Keyframe.ofFloat(0.1f, -shakeDegrees),
            Keyframe.ofFloat(0.2f, shakeDegrees),
            Keyframe.ofFloat(0.3f, -shakeDegrees),
            Keyframe.ofFloat(0.4f, shakeDegrees),
            Keyframe.ofFloat(0.5f, -shakeDegrees),
            Keyframe.ofFloat(0.6f, shakeDegrees),
            Keyframe.ofFloat(0.7f, -shakeDegrees),
            Keyframe.ofFloat(0.8f, shakeDegrees),
            Keyframe.ofFloat(0.9f, -shakeDegrees),
            Keyframe.ofFloat(1.0f, 0f)
    )
    ObjectAnimator.ofPropertyValuesHolder(this, scaleXValuesHolder, scaleYValuesHolder, rotateValuesHolder).apply {
        this.duration = duration
    }.start()

}

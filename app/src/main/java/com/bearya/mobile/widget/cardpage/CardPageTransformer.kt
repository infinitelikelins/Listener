package com.bearya.mobile.widget.cardpage

import android.graphics.Camera
import android.graphics.Matrix
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.bearya.mobile.ext.dp2px
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class CardPageTransformer(private val mViewType: Int = CARD_STACK,
                          private val mMaxShowPage: Int = 3) : ViewPager2.PageTransformer {

    companion object {
        const val POP = 1
        const val DEPTH = 2
        const val TABLET = 3
        const val FLIP_HORIZONTAL = 4
        const val BACKGROUND_TO_FOREGROUND = 5
        const val FOREGROUND_TO_BACKGROUND = 6
        const val HINGE = 7
        const val CUBE_IN_DEPTH = 8
        const val CUBE_OUT_DEPTH = 9
        const val ZOOM_IN = 10
        const val ZOOM_OUT = 11
        const val VERTICAL_SHUT = 12
        const val SPINNER = 13
        const val FAN = 14
        const val TOSS = 15
        const val CUBE_OUT_ROTATION = 16
        const val CLOCK_SPIN = 17
        const val ANTI_CLOCK_SPIN = 18
        const val FADE_OUT = 19
        const val FIDGET_SPIN = 20
        const val CUBE_IN_SCALING = 21
        const val CUBE_IN_ROTATION = 22
        const val CUBE_OUT_SCALING = 23
        const val GATE = 24
        const val FLIP_VERTICAL = 25
        const val CARD_STACK = 26
    }

    override fun transformPage(page: View, position: Float) {
        when(mViewType) {
            DEPTH -> depth(page, position)
            POP -> pop(page, position)
            TABLET -> tablet(page, position)
            FLIP_HORIZONTAL -> flipHorizontal(page, position)
            BACKGROUND_TO_FOREGROUND -> backgroundToForeground(page, position)
            FOREGROUND_TO_BACKGROUND -> foregroundToBackground(page, position)
            HINGE -> hinge(page, position)
            CUBE_IN_DEPTH -> cubeInDepth(page, position)
            CUBE_OUT_DEPTH -> cubeOutDepth(page, position)
            ZOOM_IN -> zoomIn(page, position)
            ZOOM_OUT -> zoomOut(page, position)
            VERTICAL_SHUT -> verticalShut(page, position)
            SPINNER -> spinner(page, position)
            FAN -> fan(page, position)
            TOSS -> toss(page, position)
            CUBE_OUT_ROTATION -> cubeOutRotation(page, position)
            CLOCK_SPIN -> clockSpin(page, position)
            ANTI_CLOCK_SPIN -> antiClockSpin(page, position)
            FADE_OUT -> fadeOut(page, position)
            CUBE_IN_SCALING -> cubeInScaling(page, position)
            CUBE_IN_ROTATION -> cubeInRotation(page, position)
            CUBE_OUT_SCALING -> cubeOutScaling(page, position)
            GATE -> gate(page, position)
            FLIP_VERTICAL -> flipVertical(page, position)
            FIDGET_SPIN -> fidgetSpin(page, position)
            CARD_STACK -> cardStack(page, position)
        }
        page.isClickable = position == 0f
    }

    private fun cardStack(page: View, position: Float) {
        val mScaleOffset = 8.dp2px(page.context)!!
        val mTranslationOffset = -14.dp2px(page.context)!!
        if(position <= 0f) {
            page.translationX = 0f
        } else {
            val pageWidth: Int = page.width
            page.translationX = ((mTranslationOffset - pageWidth) * position)
            val scale: Float = (pageWidth - mScaleOffset * position) / pageWidth * 1f
            page.scaleX = scale
            page.scaleY = scale
            page.translationZ = -position
            page.alpha = 1.0f
        }
    }

    private fun toss(page: View, position: Float) {
        page.translationX = -position * page.width
        page.cameraDistance = 20000F
        page.visibility = if(position < 0.5 && position > -0.5) View.VISIBLE else View.INVISIBLE
        when {
            position < -1 -> page.alpha = 0f
            position <= 0 -> {
                page.alpha = 1f
                page.scaleX = max(0.4f, (1 - abs(position)))
                page.scaleY = max(0.4f, (1 - abs(position)))
                page.rotationX = 1080 * (1 - abs(position) + 1)
                page.translationY = -1000 * abs(position)
            }
            position <= 1 -> {
                page.alpha = 1f
                page.scaleX = max(0.4f, (1 - abs(position)))
                page.scaleY = max(0.4f, (1 - abs(position)))
                page.rotationX = -1080 * (1 - abs(position) + 1)
                page.translationY = -1000 * abs(position)
            }
            else -> page.alpha = 0f
        }
    }

    private fun hinge(page: View, position: Float) {
        page.translationX = -position * page.width
        page.pivotX = 0f
        page.pivotY = 0f
        when {
            position < -1 -> page.alpha = 0f
            position <= 0 -> {
                page.rotation = 90 * abs(position)
                page.alpha = 1 - abs(position)
            }
            position <= 1 -> {
                page.rotation = 0f
                page.alpha = 1f
            }
            else -> page.alpha = 0f
        }
    }

    private fun cubeInDepth(page: View, position: Float) {
        page.cameraDistance = 20000F
        when {
            position < -1 -> page.alpha = 0f
            position <= 0 -> {
                page.alpha = 1f
                page.pivotX = page.width.toFloat()
                page.rotationY = 90 * abs(position)
            }
            position <= 1 -> {
                page.alpha = 1f
                page.pivotX = 0f
                page.rotationY = -90 * abs(position)
            }
            else -> page.alpha = 0f
        }
        if(abs(position) <= 0.5) {
            page.scaleY = max(.4f, 1 - abs(position))
        } else if(abs(position) <= 1) {
            page.scaleY = max(.4f, 1 - abs(position))
        }
    }

    private fun cubeOutDepth(page: View, position: Float) {
        when {
            position < -1 -> page.alpha = 0f
            position <= 0 -> {
                page.alpha = 1f
                page.pivotX = page.width.toFloat()
                page.rotationY = -90 * abs(position)
            }
            position <= 1 -> {
                page.alpha = 1f
                page.pivotX = 0f
                page.rotationY = 90 * abs(position)
            }
            else -> page.alpha = 0f
        }
        if(abs(position) <= 0.5) {
            page.scaleY = max(0.4f, 1 - abs(position))
        } else if(abs(position) <= 1) {
            page.scaleY = max(0.4f, 1 - abs(position))
        }
    }

    private fun cubeInScaling(page: View, position: Float) {
        page.cameraDistance = 20000f
        when {
            position < -1 -> page.alpha = 0f
            position <= 0 -> {
                page.alpha = 1f
                page.pivotX = page.width.toFloat()
                page.rotationY = 90 * abs(position)
            }
            position <= 1 -> {
                page.alpha = 1f
                page.pivotX = 0f
                page.rotationY = -90 * abs(position)
            }
            else -> page.alpha = 0f
        }
        if(abs(position) <= 0.5) {
            page.scaleY = max(.4f, 1 - abs(position))
        } else if(abs(position) <= 1) {
            page.scaleY = max(.4f, abs(position))
        }
    }

    private fun cubeInRotation(page: View, position: Float) {
        page.cameraDistance = 20000f
        when {
            position < -1 -> page.alpha = 0f
            position <= 0 -> {
                page.alpha = 1f
                page.pivotX = page.width.toFloat()
                page.rotationY = 90 * abs(position)
            }
            position <= 1 -> {
                page.alpha = 1f
                page.pivotX = 0f
                page.rotationY = -90 * abs(position)
            }
            else -> page.alpha = 0f
        }
    }

    private fun cubeOutScaling(page: View, position: Float) {
        when {
            position < -1 -> page.alpha = 0f
            position <= 0 -> {
                page.alpha = 1f
                page.pivotX = page.width.toFloat()
                page.rotationY = -90 * abs(position)
            }
            position <= 1 -> {
                page.alpha = 1f
                page.pivotX = 0f
                page.rotationY = 90 * abs(position)
            }
            else -> page.alpha = 0f
        }
        if(abs(position) <= 0.5) {
            page.scaleY = max(0.4f, 1 - abs(position))
        } else if(abs(position) <= 1) {
            page.scaleY = max(0.4f, abs(position))
        }
    }

    private fun cubeOutRotation(page: View, position: Float) {
        when {
            position < -1 -> page.alpha = 0f
            position <= 0 -> {
                page.alpha = 1f
                page.pivotX = page.width.toFloat()
                page.rotationY = -90 * abs(position)
            }
            position <= 1 -> {
                page.alpha = 1f
                page.pivotX = 0f
                page.rotationY = 90 * abs(position)
            }
            else -> page.alpha = 0f
        }
    }

    private fun fadeOut(page: View, position: Float) {
        page.translationX = -position * page.width
        page.alpha = 1 - abs(position)
    }

    private fun gate(page: View, position: Float) {
        page.translationX = -position * page.width
        when {
            position < -1 -> page.alpha = 0f
            position <= 0 -> {
                page.alpha = 1f
                page.pivotX = 0f
                page.rotationY = 90 * abs(position)
            }
            position <= 1 -> {
                page.alpha = 1f
                page.pivotX = page.width.toFloat()
                page.rotationY = -90 * abs(position)
            }
            else -> page.alpha = 0f
        }
    }

    private fun flipVertical(page: View, position: Float) {
        page.translationX = -position * page.width
        page.cameraDistance = 12000f
        page.visibility = if(position < 0.5 && position > -0.5) View.VISIBLE else View.INVISIBLE
        when {
            position < -1 -> page.alpha = 0f
            position <= 0 -> {
                page.alpha = 1f
                page.rotationY = 180 * (1 - abs(position) + 1)
            }
            position <= 1 -> {
                page.alpha = 1f
                page.rotationY = -180 * (1 - abs(position) + 1)
            }
            else -> page.alpha = 0f
        }
    }

    private fun flipHorizontal(page: View, position: Float) {
        page.translationX = -position * page.width
        page.cameraDistance = 20000f
        page.visibility = if(position < 0.5 && position > -0.5) View.VISIBLE else View.INVISIBLE
        when {
            position < -1 -> page.alpha = 0f
            position <= 0 -> {
                page.alpha = 1f
                page.rotationX = 180 * (1 - abs(position) + 1)
            }
            position <= 1 -> {
                page.alpha = 1f
                page.rotationX = -180 * (1 - abs(position) + 1)
            }
            else -> page.alpha = 0f
        }
    }

    private fun fidgetSpin(page: View, position: Float) {
        page.translationX = -position * page.width
        if(abs(position) < 0.5) {
            page.visibility = View.VISIBLE
            page.scaleX = 1 - abs(position)
            page.scaleY = 1 - abs(position)
        } else if(abs(position) > 0.5) {
            page.visibility = View.GONE
        }
        when {
            position < -1 -> page.alpha = 0f
            position <= 0 -> {
                page.alpha = 1f
                page.rotation =
                    36000 * (abs(position) * abs(position) * abs(position) * abs(position) * abs(
                        position) * abs(position) * abs(position))
            }
            position <= 1 -> {
                page.alpha = 1f
                page.rotation =
                    -36000 * (abs(position) * abs(position) * abs(position) * abs(position) * abs(
                        position) * abs(position) * abs(position))
            }
            else -> page.alpha = 0f
        }
    }

    private fun antiClockSpin(page: View, position: Float) {
        page.translationX = -position * page.width
        if(abs(position) < 0.5) {
            page.visibility = View.VISIBLE
            page.scaleX = 1 - abs(position)
            page.scaleY = 1 - abs(position)
        } else if(abs(position) > 0.5) {
            page.visibility = View.GONE
        }
        when {
            position < -1 -> page.alpha = 0f
            position <= 0 -> {
                page.alpha = 1f
                page.rotation = 360 * (1 - abs(position))
            }
            position <= 1 -> {
                page.alpha = 1f
                page.rotation = -360 * (1 - abs(position))
            }
            else -> page.alpha = 0f
        }
    }

    private fun clockSpin(page: View, position: Float) {
        page.translationX = -position * page.width
        if(abs(position) <= 0.5) {
            page.visibility = View.VISIBLE
            page.scaleX = 1 - abs(position)
            page.scaleY = 1 - abs(position)
        } else if(abs(position) > 0.5) {
            page.visibility = View.GONE
        }
        when {
            position < -1 -> page.alpha = 0f
            position <= 0 -> {
                page.alpha = 1f
                page.rotation = 360 * abs(position)
            }
            position <= 1 -> {
                page.alpha = 1f
                page.rotation = -360 * abs(position)
            }
            else -> page.alpha = 0f
        }
    }

    private fun spinner(page: View, position: Float) {
        page.translationX = -position * page.width
        page.cameraDistance = 12000F
        page.visibility = if(position < 0.5 && position > -0.5) View.VISIBLE else View.INVISIBLE
        when {
            position < -1 -> page.alpha = 0f
            position <= 0 -> {
                page.alpha = 1f
                page.rotationY = 900 * (1 - abs(position) + 1)
            }
            position <= 1 -> {
                page.alpha = 1f
                page.rotationY = -900 * (1 - abs(position) + 1)
            }
            else -> page.alpha = 0f
        }
    }

    private fun fan(page: View, position: Float) {
        page.translationX = -position * page.width
        page.pivotX = 0f
        page.pivotY = page.height / 2.0f
        page.cameraDistance = 20000f
        when {
            position < -1 -> page.alpha = 0f
            position <= 0 -> {
                page.alpha = 1f
                page.rotationY = -120 * abs(position)
            }
            position <= 1 -> {
                page.alpha = 1f
                page.rotationY = 120 * abs(position)
            }
            else -> page.alpha = 0f
        }
    }

    private fun zoomOut(page: View, position: Float) {
        val pageWidth: Int = page.width
        val pageHeight: Int = page.height
        val minScale = 0.85f
        val minAlpha = 0.5f
        when {
            position < -1 -> page.alpha = 0f
            position <= 1 -> {
                val scaleFactor = max(minScale, 1 - abs(position))
                val vertMargin = pageHeight * (1 - scaleFactor) / 2
                val horizontalMargin = pageWidth * (1 - scaleFactor) / 2
                when {
                    position >= 0 -> page.translationX = -horizontalMargin + vertMargin / 2
                    else -> page.translationX = horizontalMargin - vertMargin / 2
                }
                page.scaleX = scaleFactor
                page.scaleY = scaleFactor
                page.alpha = minAlpha + (scaleFactor - minScale) / (1 - minScale) * (1 - minAlpha)
            }
            else -> page.alpha = 0f
        }
    }

    private fun zoomIn(page: View, position: Float) {
        val scale = if(position < 0) position + 1f else abs(1f - position)
        page.scaleX = scale
        page.scaleY = scale
        page.pivotX = page.width * 0.5f
        page.pivotY = page.height * 0.5f
        page.alpha = if(position < -1f || position > 1f) 0f else 1f - (scale - 1f)
    }

    private fun verticalShut(page: View, position: Float) {
        page.translationX = -position * page.width
        page.cameraDistance = 999999999F
        page.visibility = if(position < 0.5 && position > -0.5) View.VISIBLE else View.INVISIBLE
        when {
            position < -1 -> page.alpha = 0f
            position <= 0 -> {
                page.alpha = 1f
                page.rotationX = 180 * (1 - abs(position) + 1)
            }
            position <= 1 -> {
                page.alpha = 1f
                page.rotationX = -180 * (1 - abs(position) + 1)
            }
            else -> page.alpha = 0f
        }
    }

    private fun tablet(page: View, position: Float) {
        val rotation: Float = (if(position < 0) 30f else -30f) * abs(position)
        page.translationX = getOffsetX(rotation, page.width, page.height)
        page.pivotX = page.width * 0.5f
        page.pivotY = 0f
        page.rotationY = rotation
    }

    private fun getOffsetX(rotation: Float, width: Int, height: Int): Float {
        val matrixOffset = Matrix()
        val cameraOffset = Camera()
        val tempFloatOffset = FloatArray(2)
        matrixOffset.reset()
        cameraOffset.save()
        cameraOffset.rotateY(abs(rotation))
        cameraOffset.getMatrix(matrixOffset)
        cameraOffset.restore()
        matrixOffset.preTranslate(-width * 0.5f, -height * 0.5f)
        matrixOffset.postTranslate(width * 0.5f, height * 0.5f)
        tempFloatOffset[0] = width.toFloat()
        tempFloatOffset[1] = height.toFloat()
        matrixOffset.mapPoints(tempFloatOffset)
        return (width - tempFloatOffset[0]) * if(rotation > 0.0f) 1.0f else -1.0f
    }

    private fun backgroundToForeground(page: View, position: Float) {
        val height: Int = page.height
        val width: Int = page.width
        val scale = min(if(position < 0) 1f else abs(1f - position), 1f)
        page.scaleX = scale
        page.scaleY = scale
        page.pivotX = width * 0.5f
        page.pivotY = height * 0.5f
        page.translationX = if(position < 0) width * position else -width * position * 0.25f
    }

    private fun foregroundToBackground(page: View, position: Float) {
        val height: Int = page.height
        val width: Int = page.width
        val scale = min(if(position > 0) 1f else abs(1f + position), 1f)
        page.scaleX = scale
        page.scaleY = scale
        page.pivotX = width * 0.5f
        page.pivotY = height * 0.5f
        page.translationX = if(position > 0) width * position else -width * position * 0.25f
    }

    private fun pop(page: View, position: Float) {
        page.translationX = -position * page.width
        if(abs(position) < 0.5) {
            page.visibility = View.VISIBLE
            page.scaleX = 1 - abs(position)
            page.scaleY = 1 - abs(position)
        } else if(abs(position) > 0.5) {
            page.visibility = View.GONE
        }
    }

    private fun depth(page: View, position: Float) {
        when {
            position < -1 -> page.alpha = 0f
            position <= 0 -> {
                page.alpha = 1f
                page.translationX = 0f
                page.scaleX = 1f
                page.scaleY = 1f
            }
            position <= 1 -> {
                page.translationX = -position * page.width
                page.alpha = 1 - abs(position)
                page.scaleX = 1 - abs(position)
                page.scaleY = 1 - abs(position)
            }
            else -> page.alpha = 0f
        }
    }

    fun with(viewPager2: ViewPager2?) {
        viewPager2?.offscreenPageLimit = mMaxShowPage
        viewPager2?.setPageTransformer(CardPageTransformer())
    }

}
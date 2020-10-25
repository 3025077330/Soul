package com.example.fromwork.utils

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.view.View
import android.view.animation.LinearInterpolator

/**
 * 动画工具类
 */
class AnimUtils {
    companion object {
        //旋转动画
        @JvmStatic
        fun rotation(view: View): ObjectAnimator {
            val mAnim = ObjectAnimator.ofFloat(view, "rotation", 0f, 360f)
            mAnim.setDuration(2 * 1000)
            mAnim.repeatMode = ValueAnimator.RESTART
            mAnim.repeatCount = ValueAnimator.INFINITE
            mAnim.interpolator = LinearInterpolator()
            return mAnim
        }
    }


}
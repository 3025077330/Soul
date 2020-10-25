package com.example.user.util

import android.app.AlertDialog
import android.view.View
import androidx.annotation.LayoutRes
import com.example.fromwork.R

//DiaLogUtil工具类
class DiaLogUtil {
    //防机器人验证
    companion object {
        public fun showSafety(view: View, @LayoutRes layout: Int) {
            var builder = AlertDialog.Builder(view.context)
            val view = View.inflate(view.getContext(), layout, null)
        }
    }


}
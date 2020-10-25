package com.example.fromwork.manager

import android.content.Context
import android.view.Gravity
import androidx.annotation.LayoutRes
import com.example.fromwork.R
import com.example.fromwork.view.DialogView

/**
 * 提示框管理类
 */
class DialogManager private constructor() {


    companion object {
        @JvmStatic
        val instance: DialogManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            DialogManager()
        }
    }

    fun initDiaLog(context: Context, @LayoutRes layout: Int): DialogView {
        return DialogView(context, layout, R.style.Theme_Dialog, Gravity.CENTER)
    }

    fun initDiaLog(context: Context, @LayoutRes layout: Int, gravity: Int): DialogView {
        return DialogView(context, layout, R.style.Theme_Dialog, gravity)
    }

    fun show(view: DialogView) {
        if (view != null) {
            if (!view.isShowing) {
                view.show()
            }
        }
    }

    fun hide(view: DialogView?) {
        if (view != null) {
            if (view.isShowing) {
                view.dismiss()
            }
        }
    }


}
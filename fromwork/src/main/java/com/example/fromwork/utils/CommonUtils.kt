package com.example.fromwork.utils

/**
 * 通过方法工具类
 */
class CommonUtils {

    companion object {
        @JvmStatic

        open fun isEmpty(list: List<*>?): Boolean {
            return list != null && list.size > 0
        }


    }


}
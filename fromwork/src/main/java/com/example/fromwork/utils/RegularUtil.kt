package com.example.fromwork.utils

import java.util.regex.Matcher
import java.util.regex.Pattern
import java.util.regex.PatternSyntaxException

//正则表达式工具类
class RegularUtil {


    companion object {
        /**
         * 手机号正则表达式
         */
        @Throws(PatternSyntaxException::class)
        fun isChinaPhoneLegal(str: String?): Boolean {
            val regExp = "^((13[0-9])|(15[^4])|(18[0-9])|(17[0-8])|(147,145))\\d{8}$"
            val p = Pattern.compile(regExp)
            val m: Matcher = p.matcher(str)
            return m.matches()
        }
    }


}
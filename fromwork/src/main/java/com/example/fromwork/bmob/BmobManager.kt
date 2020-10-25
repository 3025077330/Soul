package com.example.fromwork.bmob

import android.content.Context
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.BmobUser
import cn.bmob.v3.listener.FindListener

class BmobManager private constructor() {
    companion object {
        val instance: BmobManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            BmobManager()
        }
        private const val BMOB_SDK_ID = "f8efae5debf319071b44339cf51153fc"
        private const val BMOB_NEW_DOMAIN = "http://sdk.cilc.cloud/8/"
    }

    fun initBmob(context: Context) {

    }

    /**
     * 是否登录
     */
    fun isLogin(): Boolean {
        return BmobUser.isLogin()
    }

    /**
     * 获取本地对象
     */
    fun getUser(): IMUser {
        return BmobUser.getCurrentUser(IMUser::class.java)
    }

    /**
     * 查询好友
     */

    fun queryMyFriends(listener: FindListener<Friend>) {
        val query = BmobQuery<Friend>()
        query.addWhereEqualTo("user", getUser())
        query.findObjects(listener)
    }

    /**
     * 查询所有的用户
     */
    fun queryAllUser(listener: FindListener<IMUser>?) {
        val query = BmobQuery<IMUser>()
        query.findObjects(listener)
    }

    /**
     * 查询所有的广场的数据
     */
    fun queryAllSquare(listener: FindListener<SquareSet>?) {
        val query = BmobQuery<SquareSet>()
        query.findObjects(listener)
    }


}
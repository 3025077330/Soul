package com.example.soulchat.ui.star

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.example.fromwork.base.BaseMvcFragment
import com.example.fromwork.bmob.BmobManager
import com.example.fromwork.bmob.IMUser
import com.example.fromwork.manager.CloudManager
import com.example.fromwork.manager.DialogManager
import com.example.fromwork.model.IModel
import com.example.fromwork.utils.CommonUtils
import com.example.fromwork.utils.NetworkType
import com.example.fromwork.view.DialogView
import com.example.fromwork.view.LoadingView
import com.example.soulchat.R
import com.example.soulchat.adapter.CloudTagAdapter
import com.example.soulchat.model.StarModel
import com.example.soulchat.ui.UserInfoActivity
import kotlinx.android.synthetic.main.starfragment_layout.*
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity


/**
 * 星球fragment
 */
class StarFragment : BaseMvcFragment<IModel>(), View.OnClickListener {


    private var mStarList: MutableList<StarModel> = ArrayList()
    private var mAllUserList: MutableList<IMUser> = ArrayList()
    private var dialogView: DialogView? = null
    private var loadingView: LoadingView? = null
    private var cloudTagAdapter: CloudTagAdapter? = null


    private var tv_null_text: TextView? = null
    private var tv_null_cancel: TextView? = null

    override fun initModel() {

    }

    override fun initModelData() {

    }

    override fun initData() {
        cloudTagAdapter = CloudTagAdapter(activity, mStarList)
        mCloudView.setAdapter(cloudTagAdapter)
        loadingView = LoadingView(context)
        loadingView!!.setCancelable(false)
        dialogView = DialogManager.instance.initDiaLog(
            activity!!,
            R.layout.layout_star_null_item,
            Gravity.BOTTOM
        )
        tv_null_text = dialogView!!.find(R.id.tv_null_text)
        tv_null_cancel = dialogView!!.findViewById(R.id.tv_cancel)
        tv_null_cancel!!.setOnClickListener(this)


    }

    override fun initListener() {
        tv_star_title.setOnClickListener(this)
        iv_camera.setOnClickListener(this)
        iv_add.setOnClickListener(this)
        ll_random.setOnClickListener(this)
        ll_soul.setOnClickListener(this)
        ll_fate.setOnClickListener(this)
        ll_love.setOnClickListener(this)
        //监听点击事件
        mCloudView.setOnTagClickListener { parent, view, postion ->
            startUserInfo(mStarList.get(postion).userId)
        }

    }

    override fun getLayout(): Int {
        return R.layout.starfragment_layout
    }

    override fun onNetDisconnected() {

    }

    override fun onNetConnected(networkType: NetworkType?) {

    }

    override fun onClick(v: View?) {
        when (v!!) {
            tv_null_cancel -> {
                DialogManager.instance.hide(dialogView)
            }
            iv_camera -> {
                //扫描
              //  startActivity<QrCodeActivity>()
            }

        }
    }

    companion object {
        val Request_CODE = 1235;
    }

    /**
     * 跳转用户信息
     */
    private fun startUserInfo(userId: String) {
        loadingView!!.hide()
        activity!!.startActivity<UserInfoActivity>()
    }

    /**
     * 加载星球用户
     */
    private fun loadStarUser() {
        //我们从用户库中取抓取一定的好友进行匹配
        BmobManager.instance.queryAllUser(object : FindListener<IMUser>() {
            override fun done(list: MutableList<IMUser>?, p1: BmobException?) {
                p1 ?: let {
                    if (CommonUtils.isEmpty(list)) {
                        mAllUserList.clear()
                        mStarList.clear()
                        mAllUserList = list!!
                        for (index in 0..list.size) {
                            var imUser = list.get(index)
                            saveStarUser(imUser)
                        }
                        if (CloudManager.getInstance().isConnect()) {
                            //已经连接，并且星球加载，则隐藏
                            tv_connect_status.setVisibility(View.GONE);
                        }
                        cloudTagAdapter!!.notifyDataSetChanged()
                    }
                }
            }

        })


    }

    /**
     * 保存星球用户
     */
    private fun saveStarUser(imUser: IMUser) {
        var starModel = StarModel()
        starModel.userId = imUser.objectId
        starModel.nickName = imUser.nickName
        starModel.photoUrl = imUser.photo
        mStarList.add(starModel)

    }


}






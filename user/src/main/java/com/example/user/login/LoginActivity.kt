package com.example.user.login

import android.view.View
import com.example.fromwork.base.BaseMvcActivity
import com.example.fromwork.model.IModel
import com.example.fromwork.manager.DialogManager
import com.example.fromwork.utils.NetworkType
import com.example.fromwork.utils.RegularUtil
import com.example.fromwork.view.DialogView
import com.example.fromwork.view.TouchPictureV
import com.example.user.R
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class LoginActivity : BaseMvcActivity<IModel>(), View.OnClickListener {
    private var mCodeView: DialogView? = null
    private var mPictureV: TouchPictureV? = null

    override fun initModelData() {

    }

    override fun initModel() {

    }

    override fun initListener() {
        btn_login.setOnClickListener(this)
        tv_user_agreement.setOnClickListener(this)
        btn_send_code.setOnClickListener(this)
        mPictureV!!.setViewResultListener {
            DialogManager.instance.hide(mCodeView)
            sendSMS();
        }
    }

    override fun initData() {
        mCodeView = DialogManager.instance.initDiaLog(this, R.layout.dialog_code_view)
        mPictureV = mCodeView!!.findViewById<TouchPictureV>(R.id.mPictureV)

    }

    override fun getLayout(): Int {
        return R.layout.activity_login
    }

    override fun onNetDisconnected() {

    }

    override fun onNetConnected(networkType: NetworkType?) {

    }

    override fun onClick(v: View?) {
        when (v!!) {
            btn_login -> {
                //校验协议和验证码
                if (!verifyauthcode(et_code.text.toString())) {
                    toast("验证码错误")
                    return
                }
                if (!checkBox.isChecked) {
                    toast("请勾选同意用户协议")
                    return
                }
                //进行登录
            }
            btn_send_code -> {

                //判断手机格式
                if (RegularUtil.isChinaPhoneLegal(et_phone.text.toString())) {
                    //防机器人校验
                    opensafepopWindow();
                } else {
                    btn_login.setEnabled(false);
                    toast("手机格式错误，请重新输入")
                    et_phone.text.clear()
                    return
                }
            }
            tv_user_agreement -> {
                //跳转用户协议详情页面
                startActivity<PrtcolActivity>()
            }
        }
    }

    private fun opensafepopWindow() {

        DialogManager.instance.show(mCodeView!!)
    }

    fun verifyauthcode(code: String): Boolean {
        return true
    }

    fun sendSMS() {
        var strphone = et_phone.text.toString().trim();
        //请求短信验证码
    }


}
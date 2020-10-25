package com.example.user.mine

import com.example.fromwork.base.BaseMvcFragment
import com.example.fromwork.model.IModel
import com.example.fromwork.utils.NetworkType
import com.example.user.R

class MineFragment : BaseMvcFragment<IModel>() {
    override fun initModel() {

    }

    override fun initModelData() {

    }

    override fun initData() {

    }

    override fun getLayout(): Int {
        return R.layout.minefragment
    }

    override fun onNetDisconnected() {

    }

    override fun onNetConnected(networkType: NetworkType?) {

    }
}
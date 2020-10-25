package com.example.fromwork.base

import android.os.Bundle
import com.example.fromwork.model.IModel

abstract class BaseMvcActivity<T : IModel> : BaseActivity() {
    protected var iModel: T? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initModel()
        initModelData()
    }

    protected abstract fun initModelData()

    protected abstract fun initModel();


}
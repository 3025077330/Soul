package com.example.fromwork.base

import android.os.Bundle
import android.view.View
import com.example.fromwork.model.IModel

abstract class BaseMvcFragment<T : IModel> : BaseFragment() {

    protected var iModel: T? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initModel()
        initModelData()

    }

    protected abstract fun initModel();
    protected abstract fun initModelData();

}
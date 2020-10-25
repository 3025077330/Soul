package com.example.fromwork.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.example.fromwork.model.IModel
import com.example.fromwork.utils.NetStateChangeReceiver
import org.jetbrains.anko.startActivity


abstract class BaseActivity : AppCompatActivity(), NetStateChangeReceiver.NetStateChangeObserver {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        NetStateChangeReceiver.registerReceiver(this)
        initListener()
        initData();
    }

    protected abstract fun initListener();
    protected abstract fun initData();

    @LayoutRes
    protected abstract fun getLayout(): Int;


    override fun onDestroy() {
        super.onDestroy()
        NetStateChangeReceiver.unRegisterReceiver(this)
    }

    override fun onPause() {
        super.onPause()
        NetStateChangeReceiver.unRegisterObserver(this)
    }


    override fun onResume() {
        super.onResume()
        NetStateChangeReceiver.registerObserver(this)

    }

}
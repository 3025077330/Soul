package com.example.fromwork.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.example.fromwork.utils.NetStateChangeReceiver


abstract class BaseFragment : Fragment(), NetStateChangeReceiver.NetStateChangeObserver {
    private var RootView: View? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        RootView = inflater.inflate(getLayout(), container, false);
        return RootView;
    }


    protected abstract fun initData();
    protected abstract fun initListener();

    @LayoutRes
    protected abstract fun getLayout(): Int;


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        NetStateChangeReceiver.registerReceiver(context!!)
        initListener()
        initData()
    }

    override fun onDestroy() {
        NetStateChangeReceiver.unRegisterReceiver(context!!);
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        NetStateChangeReceiver.registerObserver(this);
    }

    override fun onPause() {
        super.onPause()
        NetStateChangeReceiver.unRegisterObserver(this)
    }

}
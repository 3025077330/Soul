package com.example.fromwork.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import com.example.fromwork.base.BaseApp

class NetStateChangeReceiver : BroadcastReceiver() {
    private var mType: NetworkType? = NetworkUtil.getNetworkType(BaseApp.context)

    private object InstanceHolder {
        val INSTANCE = NetStateChangeReceiver()
    }

    private val mObservers: MutableList<NetStateChangeObserver>? = ArrayList()
    override fun onReceive(context: Context, intent: Intent) {
        if (ConnectivityManager.CONNECTIVITY_ACTION == intent.action) {
            val networkType: NetworkType? = NetworkUtil.getNetworkType(context)
            notifyObservers(networkType!!)
        }
    }

    private fun notifyObservers(networkType: NetworkType) {
        if (mType === networkType) {
            return
        }
        mType = networkType
        if (networkType === NetworkType.NETWORK_NO) {
            for (observer in mObservers!!) {
                observer.onNetDisconnected()
            }
        } else {
            for (observer in mObservers!!) {
                observer.onNetConnected(networkType)
            }
        }
    }

    companion object {
        fun registerReceiver(context: Context) {
            val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
            context.registerReceiver(InstanceHolder.INSTANCE, intentFilter)
        }

        fun unRegisterReceiver(context: Context) {
            context.unregisterReceiver(InstanceHolder.INSTANCE)
        }

        fun registerObserver(observer: NetStateChangeObserver?) {
            if (observer == null) {
                return
            }
            if (!InstanceHolder.INSTANCE.mObservers!!.contains(observer)) {
                InstanceHolder.INSTANCE.mObservers.add(observer)
            }
        }

        fun unRegisterObserver(observer: NetStateChangeObserver?) {
            if (observer == null) {
                return
            }
            if (InstanceHolder.INSTANCE.mObservers == null) {
                return
            }
            InstanceHolder.INSTANCE.mObservers.remove(observer)
        }
    }

    interface NetStateChangeObserver {
        fun onNetDisconnected()
        fun onNetConnected(networkType: NetworkType?)
    }
}
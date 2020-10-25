package com.example.user.login

import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.fromwork.base.BaseActivity
import com.example.fromwork.net.Constant
import com.example.fromwork.utils.NetworkType
import com.example.user.R
import kotlinx.android.synthetic.main.activity_prtcol.*

class PrtcolActivity : BaseActivity() {
    override fun initListener() {
        
    }


    override fun initData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) webView.getSettings()
            .setLoadsImagesAutomatically(true);
        else
            webView.getSettings().setLoadsImagesAutomatically(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);


        webView.canGoBack();

        webView.goBack();

        webView.canGoForward();

        webView.goForward();

        val webSettings = webView.settings;
        webSettings.setSupportZoom(true)
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
//缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件
        webView.setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String?): Boolean {
                view.loadUrl(url!!)
                return true
            }
        })
        webView.loadUrl(Constant.AGREEMENT)


    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        webView.saveState(outState)
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) webView.goBack() else super.onBackPressed()
    }


    override fun getLayout(): Int {
        return R.layout.activity_prtcol
    }

    override fun onNetDisconnected() {

    }

    override fun onNetConnected(networkType: NetworkType?) {

    }

    override fun onDestroy() {
        webView.stopLoading()
        webView.removeAllViews()
        webView.destroy()
        super.onDestroy()
    }


}
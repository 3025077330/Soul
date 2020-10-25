package com.example.fromwork.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fromwork.R;
import com.example.fromwork.utils.AnimUtils;
import com.example.fromwork.manager.DialogManager;

/**
 * Profile: 加载提示框
 */
public class LoadingView {

    private DialogView mLodingView;
    private ImageView iv_loding;
    private TextView tv_loding_text;
    private ObjectAnimator mAnim;

    public LoadingView(Context mContext) {
        mLodingView = DialogManager.getInstance().initDiaLog(mContext, R.layout.dialog_loding);
        iv_loding = mLodingView.findViewById(R.id.iv_loding);
        tv_loding_text = mLodingView.findViewById(R.id.tv_loding_text);
        mAnim = AnimUtils.rotation(iv_loding);
    }

    /**
     * 设置加载的提示文本
     *
     * @param text
     */
    public void setLodingText(String text) {
        if (!TextUtils.isEmpty(text)) {
            tv_loding_text.setText(text);
        }
    }

    public void show() {
        mAnim.start();
        DialogManager.getInstance().show(mLodingView);
    }

    public void show(String text) {
        mAnim.start();
        setLodingText(text);
        DialogManager.getInstance().show(mLodingView);
    }

    public void hide() {
        mAnim.pause();
        DialogManager.getInstance().hide(mLodingView);
    }

    /**
     * 外部是否可以点击消失
     *
     * @param flag
     */
    public void setCancelable(boolean flag) {
        mLodingView.setCancelable(flag);
    }
}

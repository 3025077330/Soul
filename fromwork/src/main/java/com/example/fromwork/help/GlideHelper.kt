package com.example.fromwork.help

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.fromwork.R

class GlideHelper {

    companion object {
        @JvmStatic
        fun loadUrl(context: Context, url: String, imageView: ImageView) {
            context?.run {
                Glide.with(context).load(url)
                    .placeholder(R.drawable.img_glide_load_ing)
                    .error(R.drawable.img_glide_load_error)
                    .format(DecodeFormat.PREFER_RGB_565)
                    .dontAnimate()
                    .thumbnail(0.3f)
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView)
            }
        }

        @JvmStatic
        fun loadSmollUrl(context: Context, url: String, w: Int, h: Int, imageView: ImageView) {
            context?.run {
                Glide.with(context)
                    .load(url)
                    .override(w, h)
                    .placeholder(R.drawable.img_glide_load_ing)
                    .error(R.drawable.img_glide_load_error)
                    .format(DecodeFormat.PREFER_RGB_565)
                    // 取消动画，防止第一次加载不出来
                    .dontAnimate()
                    //加载缩略图
                    .thumbnail(0.3f)
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
            }
        }

    }

    interface OnGlideBitmapResultListener {
        fun onResourceReady(resource: Bitmap?)
    }
}
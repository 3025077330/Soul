package com.example.soulchat.ui


import android.media.MediaPlayer
import android.os.Handler
import android.os.Message
import android.view.KeyEvent
import com.example.fromwork.base.BaseActivity
import com.example.fromwork.utils.NetworkType
import com.example.soulchat.R
import com.example.user.login.LoginActivity
import kotlinx.android.synthetic.main.activity_wel_come.*
import org.jetbrains.anko.startActivity
import java.lang.ref.WeakReference
import java.util.*

class WelComeActivity : BaseActivity() {
    private var mediaPlayer: MediaPlayer? = null
    private var timer: Timer? = null
    private var count = 0
    private var weakHandler: WeakHandler? = null

    override fun initListener() {
        tv_skip.setOnClickListener {
            weakHandler!!.sendEmptyMessage(TERMINATION);
        }
    }

    override fun initData() {
        mediaPlayer = MediaPlayer()
        weakHandler = WeakHandler(this)
        timer = Timer()
        mediaPlayer = MediaPlayer.create(this, R.raw.backgroudmusic) //添加本地资源
        mediaPlayer!!.setLooping(true)
        mediaPlayer!!.setOnCompletionListener({ mediaPlayer!!.start() })
        mediaPlayer!!.start() //开始播放

        timer!!.schedule(object : TimerTask() {
            override fun run() {
                weakHandler!!.sendEmptyMessage(SIGNCODE)
            }
        }, 0, 1000)
    }

    override fun getLayout(): Int {
        return R.layout.activity_wel_come
    }

    override fun onNetDisconnected() {

    }

    override fun onStop() {
        super.onStop()
        mediaPlayer?.run {
            mediaPlayer!!.stop()
        }


    }


    override fun onRestart() {
        super.onRestart()
        mediaPlayer!!.start()
    }


    override fun onNetConnected(networkType: NetworkType?) {

    }


    class WeakHandler(welcomeActivity: WelComeActivity) : Handler() {
        var welcomeActivityWeakReference: WeakReference<WelComeActivity>
        override fun handleMessage(msg: Message) {
            val welcomeActivity = welcomeActivityWeakReference.get()
            welcomeActivity ?: run {
                super.handleMessage(msg)
                return
            }
            when (msg.what) {
                SIGNCODE -> {
                    welcomeActivity.count++
                    if (welcomeActivity.count == 3) {
                        welcomeActivity.timer!!.cancel()
                        welcomeActivity.mediaPlayer!!.stop()
                        welcomeActivity.mediaPlayer = null
                        if (true) welcomeActivity.startActivity<MainActivity>() else welcomeActivity.startActivity<LoginActivity>()
                        welcomeActivity.finish()
                    }
                }
                TERMINATION -> {
                    welcomeActivity.timer!!.cancel()
                    welcomeActivity.mediaPlayer!!.stop()
                    welcomeActivity.mediaPlayer = null
                    if (true) welcomeActivity.startActivity<MainActivity>() else welcomeActivity.startActivity<LoginActivity>()
                    welcomeActivity.finish()
                }
            }
        }

        init {
            welcomeActivityWeakReference = WeakReference(welcomeActivity)
        }
    }


    companion object {
        private const val SIGNCODE = 1001
        private const val TERMINATION = 1002

    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return false
    }


}
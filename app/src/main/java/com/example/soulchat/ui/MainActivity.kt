package com.example.soulchat.ui

import androidx.fragment.app.Fragment
import com.example.fromwork.base.BaseActivity
import com.example.fromwork.utils.NetworkType
import com.example.fromwork.view.BottomBar
import com.example.soulchat.R
import com.example.soulchat.ui.chat.ChatFragment
import com.example.soulchat.ui.moment.MomentFragment
import com.example.soulchat.ui.square.SquareFragment
import com.example.soulchat.ui.star.StarFragment
import com.example.user.mine.MineFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), BottomBar.IBottomBarSelectListener {
    private val fragments = arrayOf<Fragment>(
        StarFragment(),
        SquareFragment(),
        MomentFragment(),
        ChatFragment(),
        MineFragment()
    )

    private var currentfragment: Fragment? = null
    override fun initListener() {

    }

    override fun initData() {
        bottombar.setBottomBarSelectListener(this)
        fragmentswitch(0)
    }

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun onNetDisconnected() {

    }

    override fun onNetConnected(networkType: NetworkType?) {

    }

    override fun onBottomBarSelected(selectIndex: Int) {
        fragmentswitch(selectIndex)
    }

    override fun onBottomForPagerSelected(selectindex: Int) {

    }

    fun fragmentswitch(index: Int) {
        val fragment = fragments[index]
        if (fragment === currentfragment)
            return
        val beginTransaction = supportFragmentManager.beginTransaction();
        currentfragment?.run {
            beginTransaction.hide(currentfragment!!)
        }
        if (fragment.isAdded) beginTransaction.show(fragment).commit()
        else         //添加这个fragment
            beginTransaction.add(R.id.frameLayout, fragment, fragment.javaClass.simpleName)
                .commit()
        currentfragment = fragment
    }

}
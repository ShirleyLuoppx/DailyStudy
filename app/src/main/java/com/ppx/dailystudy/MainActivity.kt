package com.ppx.dailystudy

import android.content.*
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import com.ppx.dailystudy.bluetooth.BlueToothMainActivity
import com.ppx.dailystudy.broadcast.SelfDefinedBroadCastReceiver
import com.ppx.dailystudy.fragment.FragmentDemo
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Author: luoxia
 * Date: 2020/7/22 17:37
 * Description: DESCRIPTION
 *
 *
 * 在  iv_image.post {}、onWindowFocusChanged()、viewTreeObserver里面都可以获取到控件的宽高。那为什么直接在onCreate里面就获取不到呢
 * 所以说width和measuredWidth的区别是什么
 */
class MainActivity : AppCompatActivity() {
    private val TAG: String = "MainActivity"
    var up: Boolean = true
    var width: Int = 0
    var measuredWidth: Int = 0

    private val testFinishFunction: TestFinishFunction = TestFinishFunction()

    //意图过滤器
    private lateinit var intentFilter: IntentFilter
    private lateinit var myBroadCastReceiver: MyBroadCastReceiver


    //    var seekBar: SeekBar? = SeekBar(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        width = iv_image.width
        measuredWidth = iv_image.measuredWidth
        Log.d("ippx", "直接在oncreate里面获取：width:$width----measuredWidth:${measuredWidth}")

        initEvent()
        getDataByPost()
        getDataByViewTreeObserver()

        myBroadCastReceiver = MyBroadCastReceiver()
        //注册了一个监听网络变化的广播，断网和连接网络的时候，就会在onReceive()中收到消息，处理逻辑也在这个里面处理
        registerReceiver(myBroadCastReceiver, IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"))
    }

    /**
     * 新建一个广播接收器
     */
    class MyBroadCastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
//            val connectivityManager: ConnectivityManager = getSys
            Log.d("ippx", "onReceive: okin=====")
        }
    }


    /**
     * 综下：使用let会返回一个叫it的该对象，当然了，it是可以改名字的，用lamda表达式，我觉得let主要是用于非空判断的
     */
    private fun ktLetUsage() {
//        用例1
        iv_image.let {
            it.setImageResource(R.mipmap.ic_launcher_round)
        }
//        等价于,但是其实你看这里的let会有一个黄色的提示的标志，as会提示你删除掉let，因为这里的let没什么必要
        iv_image.setImageResource(R.mipmap.ic_launcher_round)

//        用例2
//        seekBar?.progress?.let {
//            PlayerManager.ximalayaPlayer.seekToByPercent(it / 100f)
    }
//        等价于，但是你看这里的seekBar?.progress是用了断言的，这样很不好，因为很多时候你都没办法判断这个参数是不是一定不为空，所以这里推荐使用let
//        PlayerManager.ximalayaPlayer.seekToByPercent(seekBar?.progress!! / 100f)
//    }

    /**
     * 综下可知：apply主要是用于设置值并且返回该对象，初始化内容较多的时候用
     * 简单来说，apply就是，方便多个设置值，且可以返回该对象
     */
    private fun ktApplyUsage() {
        //用例1
        //apply之后花括号里面就有一个对应的类型，就是说，apply里面会返回一个该类型的对象，你可以这个对象来设置值，或者用来做返回值都可以
        iv_image.apply {
            setImageResource(R.mipmap.ic_launcher_round)
        }
//        上面的用法等同于，因为这里没用到iv_image的返回值，所以其实这里看不出来什么
        iv_image.setImageResource(R.mipmap.ic_launcher_round)

        //用例2
        TestFragment().apply {
            arguments = Bundle().apply {
                putInt("test", 1)
            }
        }
//        等价于   所以说，上面的第二个apply的花括号里面返回的bundle对象就被赋值给了arguments
        val bundle = Bundle()
        bundle.putInt("test", 1)
        TestFragment().arguments = bundle
//        等价于
        TestFragment().apply {
            arguments = bundle
        }
//        等价于
        TestFragment().arguments = Bundle().apply {
            putInt("test", 1)
        }
//        如果想直接  SearchHistoryFragment().arguments = Bundle(). putInt("test", 1) 是不行 的，因为这里的putInt是没得返回值的

    }


    /**
     * 当View树的状态发生改变或者View树内部的View可见性发现改变时，onGlobalLayout方法将被回调。
     */
    private fun getDataByViewTreeObserver() {
        val viewTreeObserver = iv_image.viewTreeObserver
        viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                iv_image.viewTreeObserver.removeOnGlobalLayoutListener(this)
                Log.d(
                    "ippx",
                    "在getDataByViewTreeObserve里面获取：width:${iv_image.width}----measuredWidth:${iv_image.measuredWidth}"
                )
            }
        })
    }

    fun show() {
        fl_show_hide.visibility = View.VISIBLE
        supportFragmentManager.beginTransaction().add(
            R.id.fl_show_hide,
            FragmentDemo()
        ).commit()
    }

    fun hide() {
        fl_show_hide.visibility = View.GONE
//        supportFragmentManager.beginTransaction().hide(ShowHideFragment()).commit()
    }


    private val receiver = SelfDefinedBroadCastReceiver()
    private val selfDefinedIntentFilter = IntentFilter("intent_filter")
    private fun initEvent() {
        //点击发送一个自定义的有序广播
        bt_send_self_defined_orderedbroadcast.setOnClickListener {
            selfDefinedIntentFilter.priority = 100
            registerReceiver(receiver, selfDefinedIntentFilter)

            //参数一：action，参数二：权限相关字符串，这里传空
            sendOrderedBroadcast(Intent("intent_filter"), null)
        }

        //点击发song一个自定义的标准广播 “intent_filter”
        bt_send_self_defined_broadcast.setOnClickListener {
            //动态注册的广播接收器
            registerReceiver(receiver, selfDefinedIntentFilter)

            val intent = Intent("intent_filter")
            intent.putExtra("data", "ppx will be better and better!")
            sendBroadcast(intent)
        }

        click_finish.setOnClickListener {
            testFinishFunction.finishActivity()
        }

        bt_start_search_blue_tooth.setOnClickListener {
            startActivity(Intent(this, BlueToothMainActivity::class.java))
        }

        et_test_hide.setOnClickListener {
            hide()
        }

        et_test_call.setOnClickListener {

//            supportFragmentManager.beginTransaction().add(R.id.fl_show_hide,ShowHideFragment()).commit()
            show()
        }

        iv_image.setOnClickListener {
            width = iv_image.width
            measuredWidth = iv_image.measuredWidth
            iv_image.rotationX = 180f

            when (up) {
                true -> {
                    iv_image.rotationX = 180f
                    up = false
                }
                false -> {
                    iv_image.rotationX = 0f
                    up = true
                }
            }
            Log.d("ippx", "在setOnClickListener中获取width:${width}----2measuredWidth:${measuredWidth}")
        }
    }

    private fun getDataByPost() {
        iv_image.post {
            width = iv_image.width
            measuredWidth = iv_image.measuredWidth
            Log.d("ippx", "在 iv_image.post获取：width:${width}----measuredWidth:${measuredWidth}")
        }
    }

    /**
     * View初始化完毕后调用，当Activity的窗口得到焦点和失去焦点都会被调用一次
     */
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            width = iv_image.width
            measuredWidth = iv_image.measuredWidth
            Log.d("ippx", "在onWindowFocusChanged中获取:${width}----measuredWidth:${measuredWidth}")
        }
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ======================")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ======================")

        //销毁的时候，需要反注册这个广播接收器
        unregisterReceiver(myBroadCastReceiver)
        unregisterReceiver(receiver)
    }
}
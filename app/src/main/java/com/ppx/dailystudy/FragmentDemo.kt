package com.ppx.dailystudy

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import java.lang.ref.WeakReference

/**
 * Author: luoxia
 * Date: 2020/9/4 22:07
 * Description: 此类主要是用于了解fragment的各个生命周期的   test
 */
class FragmentDemo : Fragment() {

    private lateinit var mInflater: LayoutInflater
//    private lateinit var mRootView: WeakReference

    private val TAG = "FragmentDemo"

    //当碎片和活动建立关联的时候调用
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach: ")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: ")
    }

    //创建布局的时候调用
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView: ")

        return inflater.inflate(R.layout.fragment_show_hide,container,false)
    }

    //确保与碎片相关联的活动一定已经创建完毕的时候调用
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, "onActivityCreated: ")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
    }

    //当与碎片关联的视图被移除的饿时候调用
    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }

    //当碎片和活动解除关联的时候调用
    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "onDetach: ")
    }
}













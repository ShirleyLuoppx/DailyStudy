package com.ppx.dailystudy.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ppx.dailystudy.R

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
    //除了onCreateView，，其他的方法，如果重写了之后，都要去调用父类的实现方法，也就是super
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView: ")

        return inflater.inflate(R.layout.fragment_demo,container,false)
    }

    //确保与碎片相关联的活动一定已经创建完毕的时候调用（当activity的onCreate()返回时调用）
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

    //当与碎片关联的视图被移除的时候调用（与onCreateView相对应）
    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }

    //当碎片和活动解除关联的时候调用（与onAttach相对应）
    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "onDetach: ")
    }
}













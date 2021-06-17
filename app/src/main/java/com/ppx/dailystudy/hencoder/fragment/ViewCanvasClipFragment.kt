package com.ppx.dailystudy.hencoder.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ppx.dailystudy.R

/**
 * @Author: LuoXia
 * @Date: 2021/4/23 14:23
 * @Description: 自定义View1-4 Canvas的范围裁切和几何变换
 */
class ViewCanvasClipFragment : Fragment() {

    private val TAG = "ViewCanvasClipFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(context)
            .inflate(R.layout.fragment_canvas_clip, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: okin-----范围裁切和几何变化的fragment")
    }
}
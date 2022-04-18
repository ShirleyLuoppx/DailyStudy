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
 * @Description: 自定义View1-3 DrawText的三种方式
 */
class ViewDrawTextFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(context)
            .inflate(R.layout.fragment_view_draw_text, container, false)
    }
}
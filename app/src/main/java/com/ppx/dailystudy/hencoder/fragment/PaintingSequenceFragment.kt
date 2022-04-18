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
 * @Date: 2021/8/2 18:54
 * @Description: 自定义View1-5 绘画顺序
 */
class PaintingSequenceFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(context)
            .inflate(R.layout.fragment_painting_sequence, container, false)
    }
}
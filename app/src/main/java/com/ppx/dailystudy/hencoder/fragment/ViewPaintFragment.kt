package com.ppx.dailystudy.hencoder.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ppx.dailystudy.R

/**
 * @Author: LuoXia
 * @Date: 2021/5/14 16:07
 * @Description: 自定义View1-2 Paint相关Api的fragment
 */
class ViewPaintFragment(tags: String) : Fragment() {

    /**
     * 标志：从哪里来的fragment，需要显示隐藏哪些内容
     */
    private var tagString = tags

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(context).inflate(R.layout.fragment_view_paint, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<View>(R.id.btn_gradient_view)
            .setOnClickListener { view1: View? ->
                childFragmentManager.beginTransaction()
                    .replace(R.id.fl_paint, GradientViewFragment()).commit()
            }
        view.findViewById<View>(R.id.btn_shader_view)
            .setOnClickListener { view1: View? ->
                childFragmentManager.beginTransaction()
                    .replace(R.id.fl_paint, ShaderViewFragment()).commit()
            }
        view.findViewById<View>(R.id.btn_line_shape)
            .setOnClickListener { view1: View? ->
                childFragmentManager.beginTransaction()
                    .replace(R.id.fl_paint, LineShapeViewFragment()).commit()
            }
        view.findViewById<View>(R.id.btn_path_effect)
            .setOnClickListener { view1: View? ->
                childFragmentManager.beginTransaction()
                    .replace(R.id.fl_paint, PathEffectViewFragment()).commit()
            }
        view.findViewById<View>(R.id.btn_shader_layout)
            .setOnClickListener { view1: View? ->
                childFragmentManager.beginTransaction()
                    .replace(R.id.fl_paint, ShaderLayoutViewFragment()).commit()
            }
    }

}
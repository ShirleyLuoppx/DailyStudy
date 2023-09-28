package com.ppx.dailystudy.material.btnaviview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ppx.dailystudy.R

/**
 *
 * @Author Shirley
 * @Date：2023/9/27
 * @Desc：
 */
class Fragment1 : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(this.context).inflate(R.layout.fragment1,container)
    }
}
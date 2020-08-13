package com.ppx.dailystudy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * Author: luoxia
 * Date: 2020/7/23 15:06
 * Description: DESCRIPTION
 */
class TestFragment : Fragment() {

    var type = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.activity_main, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun testApply() {
//        用例3
        arguments?.apply {
            type = getInt("test", 1)
        }
//        等价于，但是你看这里用了断言，但是断言其实不是很好，因为有时候你是不确定这个值是不是一定不为空的，所以还是apply香
        type = arguments?.getInt("test", 1)!!
    }
}
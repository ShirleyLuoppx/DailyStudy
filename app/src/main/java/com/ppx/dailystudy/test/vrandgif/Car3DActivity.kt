package com.ppx.dailystudy.test.vrandgif

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.MotionEvent
import android.view.View.OnTouchListener
import com.ppx.dailystudy.R
import com.ppx.dailystudy.common.BaseActivity
import kotlinx.android.synthetic.main.activity_car_3d.*

/**
 *
 * @Author Shirley
 * @Date：2023/10/9
 * @Desc： 360旋转查看车辆；
 *          通过监听x轴滑动的距离来替换图片从而实现360°旋转的效果。
 *          缺点是：操作起来不是很丝滑，越大的屏幕越有这种感觉；
 *          优点是：实现方式简单，只需要50张左右不同角度的车辆的图片。
 */
class Car3DActivity : BaseActivity() {

    protected val TAG = "MainActivity"

    // 当前显示的bitmap对象
    private var bitmap: Bitmap? = null

    // 开始按下位置
    private var startX = 0

    // 当前位置
    private var currentX = 0

    // 当前图片的编号
    private var scrNum = 0

    // 图片的总数
    private val maxNum = 52

    // 资源图片集合
    private val srcs = intArrayOf(
        R.mipmap.p1,
        R.mipmap.p2,
        R.mipmap.p3,
        R.mipmap.p4,
        R.mipmap.p5,
        R.mipmap.p6,
        R.mipmap.p7,
        R.mipmap.p8,
        R.mipmap.p9,
        R.mipmap.p10,
        R.mipmap.p11,
        R.mipmap.p12,
        R.mipmap.p13,
        R.mipmap.p14,
        R.mipmap.p15,
        R.mipmap.p16,
        R.mipmap.p17,
        R.mipmap.p18,
        R.mipmap.p19,
        R.mipmap.p20,
        R.mipmap.p21,
        R.mipmap.p22,
        R.mipmap.p23,
        R.mipmap.p24,
        R.mipmap.p25,
        R.mipmap.p26,
        R.mipmap.p27,
        R.mipmap.p28,
        R.mipmap.p29,
        R.mipmap.p30,
        R.mipmap.p31,
        R.mipmap.p32,
        R.mipmap.p33,
        R.mipmap.p34,
        R.mipmap.p35,
        R.mipmap.p36,
        R.mipmap.p37,
        R.mipmap.p38,
        R.mipmap.p39,
        R.mipmap.p40,
        R.mipmap.p41,
        R.mipmap.p42,
        R.mipmap.p43,
        R.mipmap.p44,
        R.mipmap.p45,
        R.mipmap.p46,
        R.mipmap.p47,
        R.mipmap.p48,
        R.mipmap.p49,
        R.mipmap.p50,
        R.mipmap.p51,
        R.mipmap.p52
    )


    override fun initLayout(): Int {
        return R.layout.activity_car_3d
    }

    override fun initView() {
// 初始化当前显示图片编号
        // 初始化当前显示图片编号
        scrNum = 1

        imageView.setOnTouchListener(OnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> startX = event.x.toInt()
                MotionEvent.ACTION_MOVE -> {
                    currentX = event.x.toInt()
                    // 判断手势滑动方向，并切换图片
                    if (currentX - startX > 10) {
                        modifySrcR()
                    } else if (currentX - startX < -10) {
                        modifySrcL()
                    }
                    // 重置起始位置
                    startX = event.x.toInt()
                }
            }
            true
        })
    }

    // 向右滑动修改资源
    private fun modifySrcR() {
        if (scrNum > maxNum) {
            scrNum = 1
        }
        if (scrNum > 0) {
            bitmap = BitmapFactory.decodeResource(
                resources,
                srcs[scrNum - 1]
            )
            imageView.setImageBitmap(bitmap)
            scrNum++
        }
    }

    // 向左滑动修改资源
    private fun modifySrcL() {
        if (scrNum <= 0) {
            scrNum = maxNum
        }
        if (scrNum <= maxNum) {
            bitmap = BitmapFactory.decodeResource(
                resources,
                srcs[scrNum - 1]
            )
            imageView.setImageBitmap(bitmap)
            scrNum--
        }
    }
}
package com.ppx.dailystudy.hencoder.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.WindowManager
import com.ppx.dailystudy.R


/**
 * @Author: LuoXia
 * @Date: 2021/5/14 15:55
 * @Description: 自定义View 1-4 Canvas的范围裁切和几何变换
 * clipRect() 和 clipPath()
 */
class CanvasClipView(context: Context?, attrs: AttributeSet?) :
    View(context, attrs) {

    private val TAG = "CanvasClipView"

    private var mPaint: Paint = Paint()
    private lateinit var bitmap: Bitmap
    private lateinit var bitmap1: Bitmap
    private var width1 = 0
    private var height1 = 0

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.UNSPECIFIED) {
            setMeasuredDimension(
                MeasureSpec.getSize(widthMeasureSpec),
                MeasureSpec.getSize(widthMeasureSpec) * 4
            )
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Log.d(TAG, "onDraw: okin111")

        bitmap = BitmapFactory.decodeResource(resources, R.mipmap.girlpic)
        bitmap1 = BitmapFactory.decodeResource(resources, R.mipmap.pic)
        width1 = bitmap1.width
        height1 = bitmap1.height

        /**
         * 还是定义两个变量来作为x,y，应该要方便一点
         */
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val x = wm.defaultDisplay.width
        val y = wm.defaultDisplay.height
        Log.d(TAG, "onDraw: 屏幕宽高：$x，$y；图2宽高：$width1，$height1")

        /**
         * 1、范围裁切
         */
        mPaint.textSize = 60f
        canvas?.drawText("this is the original picture", 10f, 50f, mPaint)
        /**
         * 原图
         */
        canvas?.drawBitmap(bitmap, 10f, 100f, mPaint)

        canvas?.drawText("this is the picture after clipRect", 740f, 50f, mPaint)
        /**
         * 1.1、clipRect裁剪
         */
        canvas?.save()
        canvas?.clipRect(1050f, 400f, 1250f, 1000f)
        canvas?.drawBitmap(bitmap, 800f, 100f, mPaint)
        canvas?.restore()


        canvas?.drawText("clipPath", 10f, 1000f, mPaint)
        /**
         * 1.2、clipPath 裁切
         */
        canvas?.save()
        val path = Path()
        path.addCircle(500f, 1500f, 200f, Path.Direction.CCW)
        canvas?.clipPath(path)
        canvas?.drawBitmap(bitmap, 10f, 1100f, mPaint)
        canvas?.restore()

        canvas?.save()
        val path1 = Path()
        path1.fillType = Path.FillType.INVERSE_WINDING
        path1.addCircle(1050f, 1500f, 200f, Path.Direction.CW)
        canvas?.clipPath(path1)
        canvas?.drawBitmap(bitmap, 800f, 1100f, mPaint)
        canvas?.restore()

        /**
         * 2、几何变化
         */
        canvas?.drawText("几何变换", 10f, ((y - 500).toFloat()), mPaint)

        /**
         * 2.1.1 通过canvas来进行二维的几何变换   translate 平移
         */
        canvas?.drawText("canvas的二维变换", 10f, ((y - 400).toFloat()), mPaint)
        canvas?.drawBitmap(bitmap1, 10f, (y - 380).toFloat(), mPaint)

        canvas?.save()
        canvas?.translate(700f, 0f)
        canvas?.drawBitmap(bitmap1, 10f, (y - 380).toFloat(), mPaint)
        canvas?.restore()

        /**
         * 2.1.2 canvas.rotate  旋转
         * TODO：rotate：有个不加旋转轴心的api，没有作用？？
         */
        canvas?.drawBitmap(bitmap1, 10f, (y + 100).toFloat(), mPaint)

        canvas?.save()
        canvas?.translate(700f, 0f)
        canvas?.rotate(45f, 110f, (y + 200).toFloat())
        canvas?.drawBitmap(bitmap1, 10f, (y + 100).toFloat(), mPaint)
        canvas?.restore()

        /**
         * 2.1.3 canvas.scale  缩放
         */
        canvas?.drawBitmap(bitmap1, 10f, (y + 600).toFloat(), mPaint)

        canvas?.save()
        canvas?.scale(1.5f, 1.5f, 350f, (y + 700).toFloat())
        canvas?.drawBitmap(bitmap1, 400f, (y + 600).toFloat(), mPaint)
        canvas?.restore()

        canvas?.save()
        canvas?.scale(1f, 1.5f, 850f, (y + 700).toFloat())
        canvas?.drawBitmap(bitmap1, 1000f, (y + 600).toFloat(), mPaint)
        canvas?.restore()

        /**
         * 2.1.4 canvas.skew  错切
         * TODO：横向错切的为啥就不行呢....
         */
        canvas?.drawBitmap(bitmap1, 10f, (y + 1100).toFloat(), mPaint)
        canvas?.save()
        canvas?.skew(0f, 0.5f)
        canvas?.drawBitmap(bitmap1, 400f, (y + 900).toFloat(), mPaint)
        canvas?.restore()

//        canvas?.save()
//        canvas?.skew(-0.5f, 0f)
//        canvas?.drawBitmap(bitmap1, 750f, (y + 800).toFloat(), mPaint)
//        canvas?.restore()

        /**
         * 3、使用Matrix 来做二维和三维的变换
         */
        /**
         * 3.1.1 matrix.postTranslate平移 跟canvas.tranlate一样的效果
         */
        canvas?.drawText("使用Matrix进行二维和三维的变换", 10f, (2 * y - 700).toFloat(), mPaint)
        val matrix = Matrix()
        matrix.reset()
        matrix.postTranslate(200f, 0f)
        matrix.postRotate(45f, 300f, (2 * y - 500).toFloat())

        canvas?.save()
        canvas?.concat(matrix)
        canvas?.drawBitmap(bitmap1, 10f, (2 * y - 600).toFloat(), mPaint)
        canvas?.restore()

        canvas?.save()
        canvas?.scale(0.5f, 0.5f, 600f, (2 * y - 700).toFloat())
        canvas?.drawBitmap(bitmap1, 500f, (2 * y - 600).toFloat(), mPaint)
        canvas?.restore()

        canvas?.save()
        canvas?.skew(0f, 0.5f)
        canvas?.drawBitmap(bitmap1, 800f, (2 * y - 1000).toFloat(), mPaint)
        canvas?.restore()

        /**
         * 3.2 、使用 Matrix 来做自定义变换
         */
        /**
         * 3.2.1、使用setPolyToPoly点对点的方式进行变换，一共需要8个点，左上右上左下右下，两组数据，分别是源数据和变换后的数据
         * 参数1：源数据，参数2：源数据的偏移，参数2：目标移动点的数据，参数4：目标移动点的位移，参数5：采集的点的个数，不能大于4
         */
        val matrix1 = Matrix()
        val srcArray =
            floatArrayOf(
                10f,
                (2 * y - 400).toFloat(),
                210f,
                (2 * y - 400).toFloat(),
                10f,
                (2 * y - 200).toFloat(),
                210f,
                (2 * y - 200).toFloat()
            )
        val dstArray =
            floatArrayOf(
                0f,
                (2 * y - 350).toFloat(),
                330f,
                (2 * y - 490).toFloat(),
                30f,
                (2 * y - 170).toFloat(),
                230f,
                (2 * y - 140).toFloat()
            )
        matrix1.reset()
        matrix1.setPolyToPoly(srcArray, 0, dstArray, 0, 4)

        canvas?.save()
        canvas?.concat(matrix1)
        canvas?.drawBitmap(bitmap1, 200f, (2 * y - 350).toFloat(), mPaint)
        canvas?.restore()


    }
}
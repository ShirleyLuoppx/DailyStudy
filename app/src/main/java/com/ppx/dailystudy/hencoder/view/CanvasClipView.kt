package com.ppx.dailystudy.hencoder.view

import android.annotation.SuppressLint
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
    private var width1 = 0f
    private var height1 = 0f

    private var girlWidth = 0f
    private var girlHeight = 0f

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.UNSPECIFIED) {
            setMeasuredDimension(
                MeasureSpec.getSize(widthMeasureSpec),
                MeasureSpec.getSize(widthMeasureSpec) * 5
            )
        }
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Log.d(TAG, "onDraw: okin111")

        bitmap = BitmapFactory.decodeResource(resources, R.mipmap.girlpic)
        bitmap1 = BitmapFactory.decodeResource(resources, R.mipmap.pic)
        girlWidth = bitmap.width.toFloat()
        girlHeight = bitmap.height.toFloat()

        width1 = bitmap1.width.toFloat()
        height1 = bitmap1.height.toFloat()

        /**
         * 还是定义两个变量来作为x,y，应该要方便一点
         */
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val x = wm.defaultDisplay.width.toFloat()
        val y = wm.defaultDisplay.height.toFloat()
        Log.d(TAG, "onDraw: 屏幕宽高：$x，$y；图2宽高：$width1，$height1")

        /**
         * 1、范围裁切
         */
        mPaint.textSize = 60f
        canvas?.drawText("this is the original picture", 10f, 50f, mPaint)
        /**
         * 原图
         */
        canvas?.drawBitmap(bitmap, x / 2 - 50.toFloat(), 100f, mPaint)

        canvas?.drawText("this is the picture after clipRect", 10f, girlHeight + 200f, mPaint)
        /**
         * 1.1、clipRect裁剪
         * 原来以前都没明白这个裁切是怎么个裁切法：drawBitmap画的图片只能在clipRect区域内显示
         */
        canvas?.save()
        canvas?.clipRect(
            x / 2.toFloat() - 20,
            girlHeight + 210,
            x / 2.toFloat() + 100f,
            girlHeight * 2 + 50
        )
        canvas?.drawBitmap(bitmap, x / 4.toFloat(), girlHeight + 110, mPaint)
        canvas?.restore()


        canvas?.drawText("clipPath", 10f, girlHeight * 2 + 120, mPaint)
        /**
         * 1.2、clipPath 裁切
         */
        canvas?.save()
        val path = Path()
        path.addCircle(300f, girlHeight * 3 - 200, 200f, Path.Direction.CCW)
        canvas?.clipPath(path)
        canvas?.drawBitmap(bitmap, 10f, 1100f, mPaint)
        canvas?.restore()

        canvas?.save()
        val path1 = Path()
        path1.fillType = Path.FillType.INVERSE_WINDING
        path1.addCircle(x / 4 * 3.toFloat(), girlHeight * 3 - 200, 200f, Path.Direction.CW)
        canvas?.clipPath(path1)
        canvas?.drawBitmap(bitmap, x / 2.toFloat() - 30, girlHeight * 2 + 100, mPaint)
        canvas?.restore()

        /**
         * 2、几何变化
         */
        canvas?.drawText("几何变换", 10f, girlHeight * 3 + 200, mPaint)

        /**
         * 2.1.1 通过canvas来进行二维的几何变换   translate 平移
         */
        canvas?.drawText("canvas的二维变换", 10f, girlHeight * 3 + 270, mPaint)
        canvas?.drawBitmap(bitmap1, 10f, y, mPaint)

        canvas?.save()
        canvas?.translate(50f, 0f)
        canvas?.drawBitmap(bitmap1, 10f, y + height1 + 20, mPaint)
        canvas?.restore()

        /**
         * 2.1.2 canvas.rotate  旋转
         * TODO：rotate：有个不加旋转轴心的api，没有作用？？
         */
        canvas?.save()
        canvas?.translate(100f + width1, 0f)
        canvas?.rotate(45f, width1, y + height1 * 2)
        canvas?.drawBitmap(bitmap1, 10f, y + height1 + 20, mPaint)
        canvas?.restore()

        /**
         * 2.1.3 canvas.scale  缩放
         */
        canvas?.save()
        canvas?.scale(1.5f, 1.5f, 350f, (y + 700))
        canvas?.drawBitmap(bitmap1, 150f, y + 2 * height1 + 90, mPaint)
        canvas?.restore()

        canvas?.save()
        canvas?.scale(1f, 1.5f, 850f, (y + 700))
        canvas?.drawBitmap(bitmap1, width1 / 2 * 3 + 120, y + 2 * height1 + 90, mPaint)
        canvas?.restore()

        /**
         * 2.1.4 canvas.skew  错切
         * TODO：横向错切的为啥就不行呢....
         */
        canvas?.save()
        canvas?.skew(0f, 0.5f)
        canvas?.drawBitmap(bitmap1, 10f, y + height1 / 2 * 7 + 110, mPaint)
        canvas?.restore()

//        canvas?.save()
//        canvas?.skew(-0.5f, 0f)
//        canvas?.drawBitmap(bitmap1, 750f, (y + 800).toFloat(), mPaint)
//        canvas?.restore()
//
        /**
         * 3、使用Matrix 来做二维和三维的变换
         */
        /**
         * 3.1.1 matrix.postTranslate平移 跟canvas.tranlate一样的效果
         */
        canvas?.drawText("使用Matrix进行二维和三维的变换", 10f, y + height1 * 6 - 30, mPaint)
        val matrix = Matrix()
        matrix.reset()
        matrix.postTranslate(100f, 0f)
        matrix.postRotate(45f, 10f, (2 * y - 300))

        canvas?.save()
        canvas?.concat(matrix)
        canvas?.drawBitmap(bitmap1, 10f, (2 * y - 500), mPaint)
        canvas?.restore()

        canvas?.save()
        canvas?.scale(0.5f, 0.5f, 600f, 2 * y - 300)
        canvas?.drawBitmap(bitmap1, 440f, 2 * y - 200, mPaint)
        canvas?.restore()

        canvas?.save()
        canvas?.skew(0f, 0.5f)
        canvas?.drawBitmap(bitmap1, 700f, 2 * y - 700, mPaint)
        canvas?.restore()

        /**
         * 3.2 、使用 Matrix 来做自定义变换
         */
        /**
         * 3.2.1、使用setPolyToPoly点对点的方式进行变换，一共需要8个点，左上右上左下右下，两组数据，分别是源数据和变换后的数据
         * 参数1：源数据，参数2：源数据的偏移，参数2：目标移动点的数据，参数4：目标移动点的位移，参数5：采集的点的个数，不能大于4
         */
        val matrix1 = Matrix()
        val srcArray = floatArrayOf(10f, 2 * y - 400, 210f, 2 * y - 400, 10f, 2 * y - 200, 210f, 2 * y - 200)
        val dstArray = floatArrayOf(0f, 2 * y - 350, 230f, 2 * y - 490, 30f, 2 * y - 170, 230f, 2 * y - 140)
        matrix1.reset()
        matrix1.setPolyToPoly(srcArray, 0, dstArray, 0, 4)

        canvas?.save()
        canvas?.concat(matrix1)
        canvas?.drawBitmap(bitmap1, 10f, 2 * y, mPaint)
        canvas?.restore()


        /**
         * 3.3：使用Camera来做三维变换
         */
        /**
         * 3.3.1 三维旋转，大概因为是三维旋转，所以距离越大的旋转后投影出来的图片就越大，所以这里把图片画在最上面了，要不然距离y轴太远了会导致投影后的view贼大然后屏幕根本就放不下了
         * */
        canvas?.save()

        //camera 在做修改之前也是需要save状态的
        val camera = Camera()
        camera.save()
        camera.rotateX(30f)
        camera.applyToCanvas(canvas)
        camera.restore()

        canvas?.drawBitmap(bitmap1, 10f, 100f, mPaint)
        canvas?.restore()


        /**
         * 修正糊脸效果，但是貌似没啥用？？？
         */
        canvas?.save()
        canvas?.translate(0f, 500f)

        //camera 在做修改之前也是需要save状态的
        val camera1 = Camera()

        camera1.save()
        camera1.setLocation(0f, 0f, -600f)
        camera1.rotateX(40f)
        camera1.applyToCanvas(canvas)
        camera1.restore()

        canvas?.drawBitmap(bitmap1, 100f, 2 * y +800, mPaint)
        canvas?.restore()
    }
}
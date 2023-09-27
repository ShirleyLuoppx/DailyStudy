package com.ppx.dailystudy.material

import android.graphics.BitmapFactory
import android.graphics.Color
import androidx.palette.graphics.Palette
import androidx.palette.graphics.Palette.Swatch
import com.ppx.dailystudy.R
import com.ppx.dailystudy.common.BaseActivity
import kotlinx.android.synthetic.main.activity_palette_demo.*

class PaletteDemoActivity : BaseActivity() {
    override fun initLayout(): Int {
        return R.layout.activity_palette_demo
    }

    override fun initView() {
        var bitmap = BitmapFactory.decodeResource(resources, R.mipmap.meizi_2)
        Palette.from(bitmap).generate(Palette.PaletteAsyncListener { palette ->
            //柔和的深色的颜色
            var darkMuteColor = palette?.getDarkMutedColor(Color.BLUE)
            //柔和的明亮的颜色
            var lightMuteColor = palette?.getLightMutedColor(Color.BLUE)
            //活跃的深色的颜色
            var darkVibrantColor = palette?.getDarkVibrantColor(Color.BLUE)
            //活跃的明亮的颜色
            var lightVibrantColor = palette?.getLightVibrantColor(Color.BLUE)
            //获取图片中一个最柔和的颜色
            var muteColor = palette?.getMutedColor(Color.BLUE)
            //获取图片中最活跃的颜色（也可以说整个图片出现最多的颜色）
            var vibrantColor = palette?.getVibrantColor(Color.BLUE)

            tv_palette1.setBackgroundColor(darkMuteColor!!)
            tv_palette2.setBackgroundColor(lightMuteColor!!)
            tv_palette3.setBackgroundColor(darkVibrantColor!!)
            tv_palette4.setBackgroundColor(lightVibrantColor!!)
            tv_palette5.setBackgroundColor(muteColor!!)
            tv_palette6.setBackgroundColor(vibrantColor!!)


            var muteSwatch = palette?.mutedSwatch
            if (muteSwatch == null) {
                for (swatch in palette?.swatches!!) {
                    muteSwatch = swatch
                }
            }

            //谷歌推荐的：图片的整体的颜色rgb的混合值---主色调
            var rgb = muteSwatch?.rgb
            //颜色向量
            var hsl = muteSwatch?.hsl
            //谷歌推荐的：图片的整体的颜色rgb的混合值---主色调
            var bodyTextColor = muteSwatch?.bodyTextColor
            //分析该颜色在图片中所占的像素多少值
            var papulation = muteSwatch?.population
            //谷歌推荐：作为标题的颜色（有一定的和图片的对比度的颜色值）
            var titleTextColor = muteSwatch?.titleTextColor
        })
    }

    /**
     * 最终设置过透明度的颜色值
     */
    fun getTranslucentColor(percent: Float, rgb: Int): Int {
        var red = Color.red(rgb)
        var blue = Color.blue(rgb)
        var green = Color.green(rgb)
        var alpha = Color.alpha(rgb)
        alpha = Math.round(percent * alpha)
        return Color.argb(alpha, red, green, blue)
    }


}
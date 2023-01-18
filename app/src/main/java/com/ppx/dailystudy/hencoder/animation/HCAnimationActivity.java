package com.ppx.dailystudy.hencoder.animation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.PathInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.interpolator.view.animation.FastOutLinearInInterpolator;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;
import androidx.vectordrawable.graphics.drawable.ArgbEvaluator;

import com.ppx.dailystudy.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Author: LuoXia
 * @Date: 2022/12/25 21:57
 * @Description: 动画：
 * 属性动画：ViewPropertyAnimator，利用这些属性来完成平移、旋转、缩放、透明度以及速度模型的动画
 * 自定义属性动画：
 */
public class HCAnimationActivity extends AppCompatActivity {

    private String TAG = "HCAnimationActivity";
    @BindView(R.id.iv_animation)
    ImageView ivAnimation;
    @BindView(R.id.btnTranslateX)
    Button btnTranslateX;
    @BindView(R.id.btnTranslateY)
    Button btnTranslateY;
    @BindView(R.id.btnAnimation)
    Button btnAnimation;
    @BindView(R.id.btn_rwx_test)
    Button btnRwxTest;
    @BindView(R.id.test_view)
    Sample08ObjectAnimatorView testView;
    @BindView(R.id.pv_objAnimation)
    ProgressView pbView;
    @BindView(R.id.view_argb)
    ArgbGradientView argbGradientView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnTranslateX)
    void btnTranslateX() {
        //水平平移500个像素
        ivAnimation.animate().translationX(500);
    }

    @OnClick(R.id.btnTranslateY)
    void btnTranslateY() {
        //沿Y轴移动500个像素
        ivAnimation.animate().translationY(500);
    }

    @OnClick(R.id.btnAnimation)
    void btnAnimation() {
        /**
         * 插入器 ：
         * LinearInterpolator：匀速
         * LinearOutSlowInInterpolator：先匀速再变慢
         * AccelerateDecelerateInterpolator：先加速再减速
         * AccelerateInterpolator：一直加速
         * DecelerateInterpolator：一直减速
         * FastOutLinearInInterpolator：
         * AnticipateInterpolator：先有一段向左的旋转动画，再正常旋转动画
         * OvershootInterpolator：先正常旋转动画，再最后有一段向右的旋转动画
         * AnticipateOvershootInterpolator：先有一段向左的旋转动画，然后正常旋转完，最后有一段向右的旋转动画。感觉像是先跳了一下，最后又跳了一下
         * BounceInterpolator 最后会弹跳几下的效果
         * FastOutSlowInInterpolator：先快后慢的动画
         */
        ViewPropertyAnimator animator = ivAnimation.animate();
//        ivAnimation.animate().x(150).rotation(180).setDuration(2000).scaleX(1.5F).setInterpolator(new LinearInterpolator());
//        ivAnimation.animate().x(150).rotation(180).setDuration(2000).scaleX(1.5F).setInterpolator(new LinearOutSlowInInterpolator());
//        ivAnimation.animate().x(150).rotation(180).setDuration(2000).scaleX(1.5F).setInterpolator(new AccelerateDecelerateInterpolator());
//        ivAnimation.animate().x(150).rotation(180).setDuration(5000).scaleX(1.5F).setInterpolator(new AccelerateInterpolator());
//        ivAnimation.animate().x(150).rotation(180).setDuration(5000).scaleX(1.5F).setInterpolator(new DecelerateInterpolator());
//        ivAnimation.animate().x(150).rotation(180).setDuration(5000).scaleX(1.5F).setInterpolator(new FastOutLinearInInterpolator());
//        ivAnimation.animate().x(150).rotation(180).setDuration(5000).scaleX(1.5F).setInterpolator(new AnticipateInterpolator());
//        ivAnimation.animate().x(150).rotation(180).setDuration(5000).scaleX(1.5F).setInterpolator(new OvershootInterpolator());
//        ivAnimation.animate().x(150).rotation(180).setDuration(5000).scaleX(1.5F).setInterpolator(new AnticipateOvershootInterpolator());
        ivAnimation.animate().x(150).rotation(180).setDuration(5000).scaleX(1.5F).alpha(0.5F).setInterpolator(new BounceInterpolator())
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        Log.d(TAG, "onAnimationStart: ");
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        Log.d(TAG, "onAnimationEnd: ");
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        Log.d(TAG, "onAnimationCancel: ");
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                        Log.d(TAG, "onAnimationRepeat: ");
                    }
                });
//        ivAnimation.animate().x(150).rotation(180).setDuration(5000).scaleX(1.5F).setInterpolator(new FastOutSlowInInterpolator());

//        ivAnimation.animate().rotationX(90);
//        ivAnimation.animate().rotationY(90);
//        ivAnimation.animate().scaleX(100);
//        ivAnimation.animate().scaleY(100);
//        ivAnimation.animate().x(500);
//        ivAnimation.animate().y(500);
//        ivAnimation.animate().setDuration(500);
//        ivAnimation.animate().setInterpolator(new LinearInterpolator());


    }

    @OnClick(R.id.btnObjAnimator)
    void objectAnimator() {
        pbView.setProgress(50);
    }

    @OnClick(R.id.btn_rwx_test)
    void testRWXView() {
        testView.setProgress(70);
    }

    /**
     * 需要注意的是：
     * 1、自定义view 里面需要定义一个color属性并写出他的set/get方法，用于渐变的时候ObjectAnimator去设置颜色值
     * 2、ObjectAnimator需要调用start方法才能开始执行
     * 3、setColor 需要调用invalidate();  才能实现重绘效果
     */
    @SuppressLint("RestrictedApi")
    @OnClick(R.id.btn_argb_gredient)
    void testArgbGradient() {
        Log.d(TAG, "testArgbGradient: okin--------");
        ObjectAnimator animator = ObjectAnimator.ofInt(argbGradientView, "color", 0xffff0000, 0xff00ff00);
        animator.setEvaluator(new HsvTypeEvaluator());
        animator.setDuration(5000);
        animator.start();
    }
}

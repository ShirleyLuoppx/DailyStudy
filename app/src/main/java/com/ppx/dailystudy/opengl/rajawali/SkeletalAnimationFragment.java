package com.ppx.dailystudy.opengl.rajawali;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ppx.dailystudy.R;

import org.rajawali3d.animation.mesh.SkeletalAnimationObject3D;
import org.rajawali3d.animation.mesh.SkeletalAnimationSequence;
import org.rajawali3d.lights.DirectionalLight;
import org.rajawali3d.loader.md5.LoaderMD5Anim;
import org.rajawali3d.loader.md5.LoaderMD5Mesh;
import org.rajawali3d.renderer.ISurfaceRenderer;

/**
 * @Author Shirley
 * @Date：2023/10/19
 * @Desc：加载骨骼动画
 */
public class SkeletalAnimationFragment extends AExampleFragment implements View.OnClickListener {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        inflater.inflate(R.layout.fragment_skeletal_animation, mLayout, true);

        TextView tvIdle = mLayout.findViewById(R.id.tv_idel);
        tvIdle.setOnClickListener(this);
        TextView tvWalk = mLayout.findViewById(R.id.tv_walk);
        tvWalk.setOnClickListener(this);
        TextView tvArmStretch = mLayout.findViewById(R.id.tv_arm_stretch);
        tvArmStretch.setOnClickListener(this);
        TextView tvBend = mLayout.findViewById(R.id.tv_bend);
        tvBend.setOnClickListener(this);

        return mLayout;
    }

    @Override
    public ISurfaceRenderer createRenderer() {
        return new SkeletalAnimationRender(getContext(), this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_idel:
                ((SkeletalAnimationRender) mRenderer).loadSkeletalAnimation(0);
                break;
            case R.id.tv_walk:
                ((SkeletalAnimationRender) mRenderer).loadSkeletalAnimation(1);
                break;
            case R.id.tv_arm_stretch:
                ((SkeletalAnimationRender) mRenderer).loadSkeletalAnimation(2);
                break;
            case R.id.tv_bend:
                ((SkeletalAnimationRender) mRenderer).loadSkeletalAnimation(3);
                break;
        }
    }

    private final class SkeletalAnimationRender extends AExampleRenderer {
        SkeletalAnimationObject3D object3D;
        SkeletalAnimationSequence idleSequence;
        SkeletalAnimationSequence walkSequence;
        SkeletalAnimationSequence armStretch;
        SkeletalAnimationSequence bendSequence;

        public SkeletalAnimationRender(Context context, @Nullable AExampleFragment fragment) {
            super(context, fragment);
        }

        @Override
        protected void initScene() {
            //加载灯光
            DirectionalLight light = new DirectionalLight(0, -0.2f, -1.0f);
            light.setColor(1f, 1f, 1f);
            light.setPower(2);
            getCurrentScene().addLight(light);
            getCurrentCamera().setZ(8);

            try {
                Log.d("TAG", "initScene: that's ok ");
                //加载mesh模型文件
                LoaderMD5Mesh loaderMD5Mesh = new LoaderMD5Mesh(this, R.raw.ingrid_mesh);
                Log.d("TAG", "initScene: " + loaderMD5Mesh);
                loaderMD5Mesh.parse();
                Log.d("TAG", "initScene:2 ");
                object3D = (SkeletalAnimationObject3D) loaderMD5Mesh.getParsedAnimationObject();

                //加载idle的动画模型
                LoaderMD5Anim loaderMD5Anim = new LoaderMD5Anim("idle", this, R.raw.ingrid_idle);
                loaderMD5Anim.parse();
                idleSequence = (SkeletalAnimationSequence) loaderMD5Anim.getParsedAnimationSequence();

                //加载walk的动画模型
                loaderMD5Anim = new LoaderMD5Anim("walk", this, R.raw.ingrid_walk);
                loaderMD5Anim.parse();
                walkSequence = (SkeletalAnimationSequence) loaderMD5Anim.getParsedAnimationSequence();
                Log.d("TAG", "initScene: that's ok 2");
                //加载arm stretch动画模型
                loaderMD5Anim = new LoaderMD5Anim("armstretch", this, R.raw.ingrid_arm_stretch);
                loaderMD5Anim.parse();
                armStretch = (SkeletalAnimationSequence) loaderMD5Anim.getParsedAnimationSequence();

                //加载bend的动画模型
                loaderMD5Anim = new LoaderMD5Anim("bend", this, R.raw.ingrid_bend);
                loaderMD5Anim.parse();
                bendSequence = (SkeletalAnimationSequence) loaderMD5Anim.getParsedAnimationSequence();

                object3D.setAnimationSequence(idleSequence);
                object3D.setFps(24);
                object3D.setScale(0.8);
                object3D.play();

                    getCurrentScene().addChild(object3D);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void loadSkeletalAnimation(int index) {
            switch (index) {
                case 0:
                    object3D.transitionToAnimationSequence(idleSequence, 1000);
                    break;
                case 1:
                    object3D.transitionToAnimationSequence(walkSequence, 1000);
                    break;
                case 2:
                    object3D.transitionToAnimationSequence(armStretch, 1000);
                    break;
                case 3:
                    object3D.transitionToAnimationSequence(bendSequence, 1000);
                    break;
            }
        }
    }
}


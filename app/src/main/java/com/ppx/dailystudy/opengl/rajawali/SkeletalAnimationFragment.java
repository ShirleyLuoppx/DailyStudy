package com.ppx.dailystudy.opengl.rajawali;

import android.content.Context;

import androidx.annotation.Nullable;

import com.ppx.dailystudy.R;

import org.rajawali3d.loader.md5.LoaderMD5Mesh;
import org.rajawali3d.renderer.ISurfaceRenderer;

/**
 * @Author Shirley
 * @Date：2023/10/19
 * @Desc：加载骨骼动画
 */
public class SkeletalAnimationFragment extends AExampleFragment {

    @Override
    public ISurfaceRenderer createRenderer() {
        return new SkeletalAnimationRender(getContext(), this);
    }

    private final class SkeletalAnimationRender extends AExampleRenderer {

        public SkeletalAnimationRender(Context context, @Nullable AExampleFragment fragment) {
            super(context, fragment);
        }

        @Override
        protected void initScene() {
//            LoaderMD5Mesh loaderMD5Mesh = new LoaderMD5Mesh(getResources(),mTextureManager, R.raw.);
        }
    }
}


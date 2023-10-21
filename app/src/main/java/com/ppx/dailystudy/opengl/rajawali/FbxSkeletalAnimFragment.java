package com.ppx.dailystudy.opengl.rajawali;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;

import com.ppx.dailystudy.R;

import org.rajawali3d.animation.mesh.SkeletalAnimationObject3D;
import org.rajawali3d.lights.DirectionalLight;
import org.rajawali3d.loader.fbx.LoaderFBX;
import org.rajawali3d.renderer.ISurfaceRenderer;

/**
 * @Author Shirley
 * @Date：2023/10/21
 * @Desc：
 */
public class FbxSkeletalAnimFragment extends AExampleFragment {

    @Override
    public ISurfaceRenderer createRenderer() {
        return new FbxRender(getContext(), this);
    }

    private final class FbxRender extends AExampleRenderer {

        public FbxRender(Context context, @Nullable AExampleFragment fragment) {
            super(context, fragment);
        }

        @Override
        protected void initScene() {
            try {

                DirectionalLight light = new DirectionalLight();
                light.setLookAt(0f, 5f, 5f);
                light.setPower(2);
                getCurrentScene().addLight(light);

                LoaderFBX loaderFBX = new LoaderFBX(this, R.raw.fbx_file);
                Log.d("TAG", "initScene: "+loaderFBX);
                loaderFBX.parse();
                SkeletalAnimationObject3D skeletalAnimationObject3D = (SkeletalAnimationObject3D) loaderFBX.getParsedObject();
                getCurrentScene().addChild(skeletalAnimationObject3D);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}


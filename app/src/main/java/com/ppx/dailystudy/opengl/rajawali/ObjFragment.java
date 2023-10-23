package com.ppx.dailystudy.opengl.rajawali;

import android.content.Context;

import androidx.annotation.Nullable;

import com.ppx.dailystudy.R;

import org.rajawali3d.Object3D;
import org.rajawali3d.animation.Animation;
import org.rajawali3d.animation.Animation3D;
import org.rajawali3d.animation.RotateOnAxisAnimation;
import org.rajawali3d.cameras.ArcballCamera;
import org.rajawali3d.lights.DirectionalLight;
import org.rajawali3d.lights.PointLight;
import org.rajawali3d.loader.LoaderOBJ;
import org.rajawali3d.loader.ParsingException;
import org.rajawali3d.math.vector.Vector3;
import org.rajawali3d.renderer.ISurfaceRenderer;

/**
 * @Author Shirley
 * @Date：2023/10/23
 * @Desc： 基础 加载.obj 类型3d模型文件
 */
public class ObjFragment extends AExampleFragment {

    @Override
    public ISurfaceRenderer createRenderer() {
        return new ObjRender(getContext(), this);
    }

    private final class ObjRender extends AExampleRenderer {

        public ObjRender(Context context, @Nullable AExampleFragment fragment) {
            super(context, fragment);
        }

        @Override
        protected void initScene() {
            try {
                PointLight light = new PointLight();
                light.setPosition(0, 0, 4);
                light.setPower(3);
                light.setZ(2);
                getCurrentScene().addLight(light);

                DirectionalLight directionalLight = new DirectionalLight();
                directionalLight.setLookAt(0, 5, 10);
                directionalLight.setPower(3);
                getCurrentScene().addLight(directionalLight);

                //如果设置了新的相机参数的话，原本的相机的信息是不生效的
//                getCurrentCamera().setZ(22);

                LoaderOBJ loaderOBJ = new LoaderOBJ(getResources(), mTextureManager, R.raw.uploads_files_2792345_koenigsegg);//multiobjects_obj
                loaderOBJ.parse();
                Object3D object3D = loaderOBJ.getParsedObject();
                getCurrentScene().addChild(object3D);

                object3D.setScale(0.6);

                //设置拖拽功能
                ArcballCamera arcballCamera = new ArcballCamera(getContext(), getActivity().findViewById(R.id.fl_empty));
                //设置相机的位置
                arcballCamera.setPosition(1, 7, 35);
                //替换当前场景下的相机，第一个参数的旧的相机，第二个是新的
                getCurrentScene().replaceAndSwitchCamera(getCurrentCamera(), arcballCamera);

                //车辆自旋转
                Animation3D animation3D = new RotateOnAxisAnimation(Vector3.Axis.Y, 360);
                //一圈时间
                animation3D.setDurationMilliseconds(8000);
               /* 设置旋转重复类型：
                    NONE：无，转一圈后会停止
                    INFINITE：一直旋转
                    RESTART：转一圈后停止
                    REVERSE：转一圈后停止
                    REVERSE_INFINITE：顺时针一圈，逆时针一圈，如此循环
                 */
                animation3D.setRepeatMode(Animation.RepeatMode.INFINITE);
                animation3D.setTransformable3D(object3D);
                getCurrentScene().registerAnimation(animation3D);
                animation3D.play();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}


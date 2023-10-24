package com.ppx.dailystudy.opengl.rajawali;

import android.content.Context;

import androidx.annotation.Nullable;

import com.ppx.dailystudy.R;

import org.rajawali3d.Object3D;
import org.rajawali3d.animation.Animation;
import org.rajawali3d.animation.Animation3D;
import org.rajawali3d.animation.RotateOnAxisAnimation;
import org.rajawali3d.cameras.ArcballCamera;
import org.rajawali3d.debug.DebugVisualizer;
import org.rajawali3d.debug.GridFloor;
import org.rajawali3d.lights.DirectionalLight;
import org.rajawali3d.lights.PointLight;
import org.rajawali3d.loader.LoaderOBJ;
import org.rajawali3d.loader.ParsingException;
import org.rajawali3d.materials.Material;
import org.rajawali3d.materials.methods.DiffuseMethod;
import org.rajawali3d.materials.textures.CubeMapTexture;
import org.rajawali3d.math.vector.Vector3;
import org.rajawali3d.renderer.ISurfaceRenderer;

/**
 * @Author Shirley
 * @Date：2023/10/23
 * @Desc： 基础 加载.obj 类型3d模型文件
 * //加载obj文件需要注意的点：
 * //一、加载obj文件的时候，如果有纹理文件.mtl ，mtl文件里面的png 图片需要放在drawable 里面才能被识别到，如果放到mipmap的话是识别不到的。
 * //二、mtl文件的命名格式为xxx_mtl，是没有后缀的，而在.obj文件里面引用mtl文件的时候则是需要   xxx.mtl
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
                light.setPosition(0, 10, 4);
                light.setPower(3);
                getCurrentScene().addLight(light);

                DirectionalLight directionalLight = new DirectionalLight();
                directionalLight.setLookAt(0, 5, 10);
                directionalLight.setPower(3);
                getCurrentScene().addLight(directionalLight);

                //网格线
                DebugVisualizer debugViz = new DebugVisualizer(this);
                debugViz.addChild(new GridFloor());
                getCurrentScene().addChild(debugViz);

                //如果设置了新的相机参数的话，原本的相机的信息是不生效的
//                getCurrentCamera().setZ(22);

                //加载obj文件需要注意的点：
                //一、加载obj文件的时候，如果有纹理文件.mtl ，mtl文件里面的png 图片需要放在drawable 里面才能被识别到，如果放到mipmap的话是识别不到的。
                //二、mtl文件的命名格式为xxx_mtl，是没有后缀的，而在.obj文件里面引用mtl文件的时候则是需要   xxx.mtl
                LoaderOBJ loaderOBJ = new LoaderOBJ(getResources(), mTextureManager, R.raw.freigther_bi_export_obj);//multiobjects_obj
                loaderOBJ.parse();
                Object3D object3D = loaderOBJ.getParsedObject();
//                object3D.setRotX(90);
//                object3D.setRotY(0);

//                int[] fortHorse = new int[]{R.mipmap.fort_horse_brembo_spt2, R.mipmap.fort_horse_carpback
//                        , R.mipmap.fort_horse_carplate, R.mipmap.fort_horse_cloth_brightgrey
//                        , R.mipmap.fort_horse_leather, R.mipmap.fort_horse_digital_gauge
//                        , R.mipmap.fort_horse_interior_emissive_lod0, R.mipmap.fort_horse_interior_lod0
//                        , R.mipmap.fort_horse_leather_stripey_black, R.mipmap.fort_horse_lights_lod0
//                        , R.mipmap.fort_horse_misc_body, R.mipmap.fort_horse_mustang_misc
//                        , R.mipmap.fort_horse_nodamage_lod0, R.mipmap.fort_horse_plastic
//                        , R.mipmap.fort_horse_plastic_black, R.mipmap.fort_horse_plastic_black02
//                        , R.mipmap.fort_horse_plastic_dash, R.mipmap.fort_horse_resh
//                        , R.mipmap.fort_horse_undercarriage, R.mipmap.fort_horse_vehiclelights};
//
//
//                int[] mustangArray = new int[]{R.mipmap.mustang_gt_0, R.mipmap.mustang_gt_1, R.mipmap.mustang_gt_2,
//                        R.mipmap.mustang_gt_3, R.mipmap.mustang_gt_4, R.mipmap.mustang_gt_5};


                int[] resourceIds = new int[]{R.mipmap.posx, R.mipmap.negx, R.mipmap.posy, R.mipmap.negy, R.mipmap.posz, R.mipmap.negz};
//                CubeMapTexture cubeMapTexture = new CubeMapTexture("fort_horse", mustangArray);
//                cubeMapTexture.isEnvironmentTexture(true);

//                Material material = new Material();
//                //设置光线可用
//                material.enableLighting(true);
//                //设置模型对光线的漫反射方式
//                material.setDiffuseMethod(new DiffuseMethod.Lambert());
//                material.addTexture(cubeMapTexture);
//                material.setColorInfluence(0);
//                object3D.setMaterial(material);

                getCurrentScene().addChild(object3D);

                object3D.setScale(0.5);

                //设置拖拽功能
                ArcballCamera arcballCamera = new ArcballCamera(getContext(), getActivity().findViewById(R.id.fl_empty));
                //设置相机的位置
                arcballCamera.setPosition(1, 7, 10);
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
//                animation3D.play();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}


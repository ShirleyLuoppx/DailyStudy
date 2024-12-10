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

import org.rajawali3d.Object3D;
import org.rajawali3d.animation.Animation;
import org.rajawali3d.animation.SplineTranslateAnimation3D;
import org.rajawali3d.cameras.ArcballCamera;
import org.rajawali3d.curves.CatmullRomCurve3D;
import org.rajawali3d.debug.DebugVisualizer;
import org.rajawali3d.debug.GridFloor;
import org.rajawali3d.lights.DirectionalLight;
import org.rajawali3d.lights.PointLight;
import org.rajawali3d.loader.LoaderOBJ;
import org.rajawali3d.loader.ParsingException;
import org.rajawali3d.materials.Material;
import org.rajawali3d.math.vector.Vector3;
import org.rajawali3d.primitives.Line3D;
import org.rajawali3d.primitives.Sphere;
import org.rajawali3d.renderer.ISurfaceRenderer;

import java.util.Stack;

/**
 * @Author Shirley
 * @Date：2023/10/23
 * @Desc： 基础 加载.obj 类型3d模型文件
 * //加载obj文件需要注意的点：
 * //一、加载obj文件的时候，如果有纹理文件.mtl ，mtl文件里面的png 图片需要放在drawable 里面才能被识别到，如果放到mipmap的话是识别不到的。
 * //二、mtl文件的命名格式为xxx_mtl，是没有后缀的，而在.obj文件里面引用mtl文件的时候则是需要   xxx.mtl
 */
public class ObjFragment extends AExampleFragment implements View.OnClickListener {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        inflater.inflate(R.layout.fragment_load_obj, mLayout, true);

        TextView tvTop = mLayout.findViewById(R.id.btn_top);
        tvTop.setOnClickListener(this);
        TextView tvBottom = mLayout.findViewById(R.id.btn_bottom);
        tvBottom.setOnClickListener(this);
        TextView tvLeft = mLayout.findViewById(R.id.btn_left);
        tvLeft.setOnClickListener(this);
        TextView tvRight = mLayout.findViewById(R.id.btn_right);
        tvRight.setOnClickListener(this);
        TextView tvFront = mLayout.findViewById(R.id.btn_front);
        tvFront.setOnClickListener(this);
        TextView tvBack = mLayout.findViewById(R.id.btn_back);
        tvBack.setOnClickListener(this);

        return mLayout;
    }

    @Override
    public ISurfaceRenderer createRenderer() {
        return new ObjRender(getContext(), this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_top:
                ((ObjRender) mRenderer).setArcballCameraPosition(0);
                break;
            case R.id.btn_bottom:
                ((ObjRender) mRenderer).setArcballCameraPosition(1);
                break;
            case R.id.btn_left:
                ((ObjRender) mRenderer).setArcballCameraPosition(2);
                break;
            case R.id.btn_right:
                ((ObjRender) mRenderer).setArcballCameraPosition(3);
                break;
            case R.id.btn_front:
                ((ObjRender) mRenderer).setArcballCameraPosition(4);
                break;
            case R.id.btn_back:
                ((ObjRender) mRenderer).setArcballCameraPosition(5);
                break;
            default:
                break;
        }
    }


    private final class ObjRender extends AExampleRenderer {

        private ArcballCamera arcballCamera;
        private Object3D object3D;
        double x = 0;
        double y = 0;
        double z = 0;

        public ObjRender(Context context, @Nullable AExampleFragment fragment) {
            super(context, fragment);
        }

        @Override
        protected void initScene() {
            try {
                addLight();
                addGridFloor();

                //如果设置了新的相机参数的话，原本的相机的信息是不生效的
//                getCurrentCamera().setZ(22);

                //加载obj文件需要注意的点：
                //一、加载obj文件的时候，如果有纹理文件.mtl ，mtl文件里面的png 图片需要放在drawable 里面才能被识别到，如果放到mipmap的话是识别不到的。
                //二、mtl文件的命名格式为xxx_mtl，是没有后缀的，而在.obj文件里面引用mtl文件的时候则是需要   xxx.mtl
                LoaderOBJ loaderOBJ = new LoaderOBJ(getResources(), mTextureManager, R.raw.freigther_bi_export_obj);//multiobjects_obj
                loaderOBJ.parse();
                object3D = loaderOBJ.getParsedObject();
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


//                int[] resourceIds = new int[]{R.mipmap.posx, R.mipmap.negx, R.mipmap.posy, R.mipmap.negy, R.mipmap.posz, R.mipmap.negz};
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
                arcballCamera = new ArcballCamera(getContext(), getActivity().findViewById(R.id.fl_empty));
                //设置相机的位置
                x = 1;
                y = 7;
                z = 10;
                arcballCamera.setPosition(x, y, z);
                //替换当前场景下的相机，第一个参数的旧的相机，第二个是新的
                getCurrentScene().replaceAndSwitchCamera(getCurrentCamera(), arcballCamera);


                //添加箭头，就当箭头是车门了
                addArrowAnimation();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Object3D arrow;
        private void addArrowAnimation() {
            Material material;
            try {
                LoaderOBJ parser = new LoaderOBJ(mContext.getResources(),
                        mTextureManager, R.raw.arrow);

                parser.parse();

                material = new Material();
                arrow = parser.getParsedObject();
                arrow.setMaterial(material);
                arrow.setScale(.5f);
                arrow.setColor(0xffffff00);
                arrow.setPosition(-1.5, 1, 1);// x = -5;y = 2;z = 1;
                getCurrentScene().addChild(arrow);
            } catch (ParsingException e) {
                throw new RuntimeException(e);
            }

            // -- create a catmull-rom path. The first and the last point are control points.
            CatmullRomCurve3D path = new CatmullRomCurve3D();
            float r = 12;
            float rh = r * .5f;

            //轨迹点
            for (int i = 0; i < 4; i++) {
                Vector3 vector3 = new Vector3(-rh + (Math.random() * r), -rh
                        + (Math.random() * r), -rh + (Math.random() * r));
                Log.d("TAG", "addArrowAnimation: "+vector3.x + "-" + vector3.y + "-" + vector3.z);
                path.addPoint(vector3);
            }

            //沿着轨迹动的动画
            final SplineTranslateAnimation3D anim = new SplineTranslateAnimation3D(path);
            anim.setDurationMilliseconds(5000);
            anim.setRepeatMode(Animation.RepeatMode.REVERSE_INFINITE);
            // -- orient to path
            anim.setOrientToPath(true);
            anim.setTransformable3D(arrow);
            getCurrentScene().registerAnimation(anim);
            anim.play();

            int numPoints = path.getNumPoints();
//            Log.d("TAG", "addArrowAnimation: " + numPoints);

            for (int i = 0; i < numPoints; i++) {
                Sphere s = new Sphere(.2f, 6, 6);
                s.setMaterial(material);
                s.setPosition(path.getPoint(i));

//                if (i == 0)
                    s.setColor(0xffff0000);
//                else if (i == numPoints - 1)
//                    s.setColor(0xff0066ff);
//                else
//                    s.setColor(0xff999999);
                //这个是添加的路径上的点
                getCurrentScene().addChild(s);
            }

            // -- visualize the line
            Stack<Vector3> linePoints = new Stack<>();
            for (int i = 0; i < 100; i++) {
                Vector3 point = new Vector3();
                path.calculatePoint(point, i / 100f);
                linePoints.add(point);
            }

//            Log.d("TAG", "addArrowAnimation: linePoints.size  = "+linePoints.size());
            //3d轨迹线
            Line3D line = new Line3D(linePoints, 1, 0xffffffff);
            line.setMaterial(material);
            getCurrentScene().addChild(line);
        }

        /**
         * 添加灯光
         */
        private void addLight() {
            PointLight light = new PointLight();
            light.setPosition(0, 10, 4);
            light.setPower(3);
            getCurrentScene().addLight(light);

            DirectionalLight directionalLight = new DirectionalLight();
            directionalLight.setLookAt(0, 5, 10);
            directionalLight.setPower(3);
            getCurrentScene().addLight(directionalLight);
        }

        /**
         * 添加网格线
         */
        private void addGridFloor() {
            DebugVisualizer debugViz = new DebugVisualizer(this);
            debugViz.addChild(new GridFloor());
            getCurrentScene().addChild(debugViz);
        }

        public void setArcballCameraPosition(int type) {
            //先设置方向到最开始程序运行出来的模型的位置
            arcballCamera.setPosition(1, 7, 10);
            //再重置相机位置
            arcballCamera.resetCameraOrientation();

            switch (type) {
                case 0:
                    x = 1;
                    y = 7;
                    z = 0;
                    break;
                case 1:
                    x = 1;
                    y = -7;
                    z = 0;
                    break;
                case 2:
                    x = -5;
                    y = 2;
                    z = 1;
                    break;
                case 3:
                    x = 5;
                    y = 2;
                    z = 1;
                    break;
                case 4:
                    x = 0;
                    y = 1;
                    z = 5;
                    break;
                case 5:
                    x = 0;
                    y = 1;
                    z = -5;
                    break;
                default:
                    break;
            }
            object3D.setScale(0.3);
            arrow.setScale(.5f);
            //切换视角
            arcballCamera.setPosition(x, y, z);
            Log.d("TAG", "setArcballCameraPosition: " + arcballCamera.getPosition().x + "-" + arcballCamera.getPosition().y + "-" + arcballCamera.getPosition().z);
        }
    }
}


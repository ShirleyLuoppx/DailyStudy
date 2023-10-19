package com.ppx.dailystudy.opengl.rajawali;

import android.content.Context;

import androidx.annotation.Nullable;

import com.ppx.dailystudy.R;

import org.rajawali3d.Object3D;
import org.rajawali3d.cameras.ArcballCamera;
import org.rajawali3d.lights.DirectionalLight;
import org.rajawali3d.loader.LoaderAWD;
import org.rajawali3d.materials.Material;
import org.rajawali3d.materials.methods.DiffuseMethod;
import org.rajawali3d.materials.textures.CubeMapTexture;
import org.rajawali3d.renderer.ISurfaceRenderer;

/**
 * @Author Shirley
 * @Date：2023/10/19
 * @Desc：加载awd后缀的3d模型文件
 */
public class AwdModelFragment extends AExampleFragment {


    @Override
    public ISurfaceRenderer createRenderer() {
        return new AwdModelRender(getContext(), this);
    }

    private final class AwdModelRender extends AExampleRenderer {

        public AwdModelRender(Context context, @Nullable AExampleFragment fragment) {
            super(context, fragment);
        }

        @Override
        protected void initScene() {
            try {
                //添加平行光线
                DirectionalLight directionalLight = new DirectionalLight();
                //设置光线的发出点
                directionalLight.setLookAt(15, 15, 5);
                directionalLight.enableLookAt();
                //设置光线的强度，强度越大，光线越亮，值可以为正负
                directionalLight.setPower(2f);
                //给当前场景添加光线
                getCurrentScene().addLight(directionalLight);

                //设置光线的发出点
                directionalLight.setLookAt(-1, 1, -1);
                directionalLight.enableLookAt();
                //设置光线的强度，强度越大，光线越亮，值可以为正负
                directionalLight.setPower(2f);
                //给当前场景添加光线
                getCurrentScene().addLight(directionalLight);
                getCurrentCamera().setZ(5);

                //加载3D模型文件
                LoaderAWD loaderAWD = new LoaderAWD(getResources(), mTextureManager, R.raw.awd_monkey);
                loaderAWD.parse();
                //获取猴子模型的3d对象
                Object3D monkeyObj3D = loaderAWD.getParsedObject();

                //设置模型的样式
                Material monkeyMaterial = new Material();
                //设置光线可用
                monkeyMaterial.enableLighting(true);
                //设置模型对光线的漫反射方式
                monkeyMaterial.setDiffuseMethod(new DiffuseMethod.Lambert());
                //设置颜色样式
                monkeyMaterial.setColor(0x990000);
                //设置样式的颜色影响：
                // 当模型只有单纯的颜色样式的时候，setColorInfluence(0) 则会将模型的颜色样式就会失效，看起来就是灰色的一个模型；
                // 当模型添加了纹理贴图后，再设置颜色影响为0则 相当于这个模型就看不到这个颜色样式，只能看到纹理贴图
//                monkeyMaterial.setColorInfluence(0);

                //纹理贴图数组
                int[] resourceIds = new int[]{R.mipmap.posx, R.mipmap.negx, R.mipmap.posy, R.mipmap.negy, R.mipmap.posz, R.mipmap.negz};
                CubeMapTexture cubeMapTexture = new CubeMapTexture("skyPics", resourceIds);
                cubeMapTexture.isEnvironmentTexture(true);
                monkeyMaterial.addTexture(cubeMapTexture);
                //添加了纹理贴图后就需要设置颜色影响为0了
                monkeyMaterial.setColorInfluence(0);

                //将样式添加到3d模型
                monkeyObj3D.setMaterial(monkeyMaterial);
                //将猴子3d模型对象加入到当前场景中
                getCurrentScene().addChild(monkeyObj3D);

                //设置拖拽功能
                ArcballCamera arcballCamera = new ArcballCamera(getContext(), getActivity().findViewById(R.id.fl_empty));
                //设置相机的位置
                arcballCamera.setPosition(1, 1, 5);
                //替换当前场景下的相机，第一个参数的旧的相机，第二个是新的
                getCurrentScene().replaceAndSwitchCamera(getCurrentCamera(), arcballCamera);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}


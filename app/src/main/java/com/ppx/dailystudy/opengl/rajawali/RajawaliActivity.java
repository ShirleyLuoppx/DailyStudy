package com.ppx.dailystudy.opengl.rajawali;

import android.view.View;
import android.widget.TextView;

import com.ppx.dailystudy.R;
import com.ppx.dailystudy.common.BaseActivity;

import butterknife.BindView;

/**
 * @Author Shirley
 * @Date：2023/10/18
 * @Desc：
 */

public class RajawaliActivity extends BaseActivity {

    @BindView(R.id.tv_load_awd)
    TextView tvLoadAwd;
    @BindView(R.id.tv_load_animation_model)
    TextView tvLoadAnimModel;

    @Override
    protected int initLayout() {
        return R.layout.activity_load_awd_file;
    }

    @Override
    protected void initView() {

    }

    //在xml里面使用  onClick 跳转方法的时候，函数必须为public的，要不然会报错找不到方法
    public void loadAwdModel(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_empty, new AwdModelFragment()).commit();
    }

    public void loadAnimationModel(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_empty, new SkeletalAnimationFragment()).commit();
    }
}


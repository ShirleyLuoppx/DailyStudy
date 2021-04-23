package com.ppx.dailystudy.hencoder.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ppx.dailystudy.R;

/**
 * @Author: LuoXia
 * @Date: 2021/4/6 18:58
 * @Description: HenCoder自定义View
 */
public class HenCoderViewFragment extends Fragment {
    /**
     * 标志：从哪里来的fragment，需要显示隐藏哪些内容
     */
    private String tag;

    public HenCoderViewFragment(String tag) {
        this.tag = tag;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (tag.equals(ViewType.BASE_API.name())) {
            return LayoutInflater.from(getContext()).inflate(R.layout.activity_test_customize_view, container, false);
        } else if (tag.equals(ViewType.FILL_TYPE.name())) {
            return LayoutInflater.from(getContext()).inflate(R.layout.fragment_hencoder_view_filltype, container, false);
        } else if (tag.equals(ViewType.PAINT.name())) {
            return LayoutInflater.from(getContext()).inflate(R.layout.fragment_hencoder_view_paint, container, false);
        } else if (tag.equals(ViewType.TEXT.name())) {
            return LayoutInflater.from(getContext()).inflate(R.layout.fragment_hencoder_view_paint, container, false);
        } else {
            return LayoutInflater.from(getContext()).inflate(R.layout.fragment_hencoder_view_filltype, container, false);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.btn_gradient_view).setOnClickListener(view1 -> getChildFragmentManager().beginTransaction().replace(R.id.fl_paint,new GradientViewFragment()).commit());
        view.findViewById(R.id.btn_shader_view).setOnClickListener(view1 -> getChildFragmentManager().beginTransaction().replace(R.id.fl_paint,new ShaderViewFragment()).commit());
        view.findViewById(R.id.btn_line_shape).setOnClickListener(view1 -> getChildFragmentManager().beginTransaction().replace(R.id.fl_paint,new LineShapeViewFragment()).commit());
        view.findViewById(R.id.btn_path_effect).setOnClickListener(view1 -> getChildFragmentManager().beginTransaction().replace(R.id.fl_paint,new PathEffectViewFragment()).commit());
        view.findViewById(R.id.btn_shader_layout).setOnClickListener(view1 -> getChildFragmentManager().beginTransaction().replace(R.id.fl_paint,new ShaderLayoutViewFragment()).commit());
        view.findViewById(R.id.btn_draw_text).setOnClickListener(view1 -> getChildFragmentManager().beginTransaction().replace(R.id.fl_paint,new ViewDrawTextFragment()).commit());
    }

    enum ViewType{
        /**
         * 自定义view1-1 的一些基础api
         */
        BASE_API,
        /**
         * 自定义view  1-1 的fill_type  部分
         */
        FILL_TYPE,
        /**
         * 自定义view  1-2  的paint 部分
         */
        PAINT,
        /**
         * 自定义View 1-3 的Text部分
         */
        TEXT;
    }
}

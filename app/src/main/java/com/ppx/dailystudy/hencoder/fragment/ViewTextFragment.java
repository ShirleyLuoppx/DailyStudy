package com.ppx.dailystudy.hencoder.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ppx.dailystudy.R;
import com.ppx.dailystudy.hencoder.ViewType;

/**
 * @Author: LuoXia
 * @Date: 2021/4/6 18:58
 * @Description: 自定义View 1-3 Text 相关的api的fragment
 * 这是第二个提交
 */
public class ViewTextFragment extends Fragment {
    /**
     * 标志：从哪里来的fragment，需要显示隐藏哪些内容
     */
    private String tag;

    public ViewTextFragment(String tag) {
        this.tag = tag;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (tag.equals(ViewType.BASE_API.name())) {
            return LayoutInflater.from(getContext()).inflate(R.layout.activity_test_customize_view, container, false);
        } else if (tag.equals(ViewType.FILL_TYPE.name())) {
            return LayoutInflater.from(getContext()).inflate(R.layout.fragment_hencoder_view_filltype, container, false);
        } else if (tag.equals(ViewType.TEXT.name())) {
            return LayoutInflater.from(getContext()).inflate(R.layout.fragment_hencoder_view_paint, container, false);
        } else {
            return LayoutInflater.from(getContext()).inflate(R.layout.fragment_hencoder_view_filltype, container, false);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.btn_draw_text).setOnClickListener(view1 -> getChildFragmentManager().beginTransaction().replace(R.id.fl_paint, new ViewDrawTextFragment()).commit());
        view.findViewById(R.id.btn_show_text_style).setOnClickListener(view1 -> getChildFragmentManager().beginTransaction().replace(R.id.fl_paint, new ViewTextShowStyleFragment()).commit());
        view.findViewById(R.id.btn_measure_text_size).setOnClickListener(view1 -> getChildFragmentManager().beginTransaction().replace(R.id.fl_paint, new ViewMeasureTextSizeFragment()).commit());
    }
}

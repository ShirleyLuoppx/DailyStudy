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

        if (tag.equals("FILL_TYPE")) {
            return LayoutInflater.from(getContext()).inflate(R.layout.fragment_hencoder_view_filltype, container, false);
        } else if (tag.equals("PAINT")) {
            return LayoutInflater.from(getContext()).inflate(R.layout.fragment_hencoder_view_paint, container, false);
        } else if (tag.equals("BASE_API")) {
            return LayoutInflater.from(getContext()).inflate(R.layout.activity_test_customize_view, container, false);
        } else {
            return LayoutInflater.from(getContext()).inflate(R.layout.fragment_hencoder_view_filltype, container, false);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}

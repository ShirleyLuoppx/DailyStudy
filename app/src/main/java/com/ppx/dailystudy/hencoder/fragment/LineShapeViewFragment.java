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
 * @Date: 2021/4/16 15:03
 * @Description: 自定义view1-2 线条形状的fragment
 */
public class LineShapeViewFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_line_shape_view,container,false);
    }
}

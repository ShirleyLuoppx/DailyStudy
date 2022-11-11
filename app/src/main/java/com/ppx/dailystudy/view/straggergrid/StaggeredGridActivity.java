package com.ppx.dailystudy.view.straggergrid;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.ppx.dailystudy.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: LuoXia
 * @Date: 2022/11/11 18:13
 * @Description: StraggerGrid瀑布流布局测试
 */
public class StaggeredGridActivity extends AppCompatActivity {

    private List<StaggeredBook> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staggeredgrid);

        initData();
    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            StaggeredBook book = new StaggeredBook("《Android 开发艺术与探索》" + i, (int) (Math.random() * 300 + 200));
            list.add(book);
        }

        RecyclerView recyclerView = findViewById(R.id.rv);
        StaggeredViewAdapter adapter = new StaggeredViewAdapter(list, this);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
    }
}

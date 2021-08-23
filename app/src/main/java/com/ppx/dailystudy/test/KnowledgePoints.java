package com.ppx.dailystudy.test;

import android.annotation.SuppressLint;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;

import androidx.appcompat.app.AppCompatActivity;

import com.ppx.dailystudy.R;

/**
 * @Author: LuoXia
 * @Date: 2021/7/29 18:04
 * @Description: 知识点测试类
 */
public class KnowledgePoints extends AppCompatActivity {

    public static void main(String[] args) {

    }

    /**
     * 测试从自定义样式的xml里面获取数值？
     */
    private void getDataFromXml() {
        //获取自定义属性的xml文件
        TypedArray array = getApplicationContext().obtainStyledAttributes(null, R.styleable.BubbleView);
        //从array中获取自定义属性的值
        float mArrowWidth = array.getDimension(R.styleable.BubbleView_arrowWidth, 25);
        //用完及时回收
        array.recycle();
    }

    /**
     * 新建一个SparseArray
     */
    @SuppressLint("UseSparseArrays")
    private final SparseArray<Integer> sparseArray = new SparseArray<>();

    {
        sparseArray.put(1, 1);
        sparseArray.put(2, 2);
        sparseArray.put(3, 3);
        sparseArray.put(4, 4);
    }

    private final SparseIntArray sparseIntArray = new SparseIntArray();

    {
        sparseIntArray.put(1, 1);
        sparseIntArray.put(2, 2);
    }

    private final SparseBooleanArray sparseBooleanArray = new SparseBooleanArray();

    {
        sparseBooleanArray.append(0, true);
        sparseBooleanArray.append(1, true);
    }


    private void test() {
        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
            }
        };

        handler.sendMessage(null);

        Message.obtain().sendToTarget();
    }
}

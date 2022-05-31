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

import org.jetbrains.annotations.TestOnly;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: LuoXia
 * @Date: 2021/7/29 18:04
 * @Description: 知识点测试类
 */
public class KnowledgePoints extends AppCompatActivity {

    private static final String TAG = "KnowledgePoints";

    public static void main(String[] args) {
//        getTime();

//        KotlinDemo.INSTANCE.filterTest();

//        testArraysFillFun();
//        singletonListTest();
        AtomicIntegerSet();
    }

    /**
     * 测试 AtomicInteger.set(1|3)
     * 需要注意的是：这里的重点并不是AtomicInteger.set，而是set里面参数的  |  ,表示“或”，是需要将对应的十进制转换成二进制后，列竖式，有1 则为1 ，最终再转成十进制的结果
     *
     * eg：
     *  AtomicInteger.set(1 | 22);
     *  1         0001
     *  22      1 0110
     *  --------------
     *  result  1 0111   -->对应的十进制则为23
     *
     *  总结：
     *  | 或  ，有1则为1；
     *  & 与  ，同为1则为1；
     *  ^ 异或  ，不同为1则为1；
     *  ~ 取反，这个有补码啥的，先记前三个吧
     */
    @TestOnly
    public static void AtomicIntegerSet() {
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.set(1 | 22);
        System.out.println("AtomicIntegerSet the last value is " + atomicInteger.get());
    }

    /**
     * Collections.singletonList：只用一句代码将一个object类型的对象转换成为一个对应类型的List集合。
     * params：object类型的参数
     * return：对应类型的List集合
     * <p>
     * java 9及以后的版本中增加了一个：List.of();  支持传入1-10的参数，最后将所有参数都加入这个List集合。
     * params：1-10个同类型的参数
     * return：一个对应类型的List集合
     */
    public static void singletonListTest() {
        List<Integer> integerList = Collections.singletonList(1);
        for (Integer integer : integerList) {
            System.out.println("singletonListTest:" + integer);
        }
    }

    /**
     * 测试 java原生接口 Arrays.fill()
     * Arrays.fill(int[] a, int val)：用val的指去覆盖a数组中的每一个数据。其他重载方法含义类似
     * 重写override和重载overload
     * 重写override：重写是子类对父类的允许访问的方法的实现过程进行重新编写, 返回值和形参都不能改变。即外壳不变，核心重写！
     * 重载overload：方法名一致，参数不一致（参数个数，参数类型，参数顺序）
     */
    public static void testArraysFillFun() {
        int[] intArrays = new int[]{5, 1, 0, 9, 7};

        for (int index : intArrays) {
            System.out.println("testArraysFillFun  intArrays = " + index);
        }

        System.out.println("---------------------------------");
        Arrays.fill(intArrays, 66);

        for (int index : intArrays) {
            System.out.println("testArraysFillFun  intArrays = " + index);
        }
    }

    public static void getTime() {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Long second = format.parse("2021-7-24 00:00:00").getTime();
            System.out.println(second);
        } catch (Exception e) {
            System.out.println("error:" + e.toString());
        }
    }

    /**
     * 测试从自定义样式的xml里面获取数值？
     * 不是的，他应该是在xml布局页面里面使用自定义view的时候使用了自定义属性且赋了值
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

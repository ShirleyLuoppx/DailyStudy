package com.ppx.dailystudy.designmode;

/**
 * @Author: LuoXia
 * @Date: 2021/8/6 10:50
 * @Description: 单例模式中synchronized的重要性
 */
public class SingletonSynchronizedTest {

    private static SingletonSynchronizedTest mInstance;

    public SingletonSynchronizedTest getInstance() {
//        mInstance = getInstanceFromNewObj();
//        mInstance = getInstanceFromStaticMethod();
        mInstance = getInstanceFromStaticCodes();
        return mInstance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            MyThread myThread = new MyThread();
            myThread.start();
        }
    }

    public void test() {
        synchronized (SingletonSynchronizedTest.class) {
            System.out.println("run: 开始...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("run: 结束..");
        }
    }

    /**
     * 实现一，直接new
     * 多线程同时获取的时候每次都会new一个新的对象，浪费资源
     */
    private SingletonSynchronizedTest getInstanceFromNewObj() {
        mInstance = new SingletonSynchronizedTest();
        return mInstance;
    }

    /**
     * 实现二，synchronized加在方法名前
     * 不用每次都生成一个实例对象，只在第一次访问的时候生成一个，节约资源
     * 同步了整个方法，如果a线程在访问这个方法，那么b线程就不能同时访问，必须等a线程访问完毕后b线程才能访问
     */
    private synchronized SingletonSynchronizedTest getInstanceFromStaticMethod() {
        if (mInstance == null) {
            mInstance = new SingletonSynchronizedTest();
        }
        return mInstance;
    }

    /**
     * 实现三，synchronized加在代码块前
     * 同步代码块，使得多线程同时访问这个方法的时候，持有这个类对象锁的线程先访问这个代码块，
     * 其他线程可以同时访问这个方法中其他代码，等持有锁的线程访问完并释放锁后，其他线程才能访问这个代码块，
     * 相比同步整个代码块，可以多线程同时访问方法中除同步代码块以外的代码
     */
    private SingletonSynchronizedTest getInstanceFromStaticCodes() {
        synchronized (SingletonSynchronizedTest.class) {
            if (mInstance == null) {
                mInstance = new SingletonSynchronizedTest();
            }
        }
        return mInstance;
    }
}

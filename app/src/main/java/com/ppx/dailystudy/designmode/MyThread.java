package com.ppx.dailystudy.designmode;

/**
 * @Author: LuoXia
 * @Date: 2021/8/6 11:22
 * @Description:
 */
public class MyThread extends Thread {
    @Override
    public void run() {
        super.run();
        SingletonSynchronizedTest mInstance = new SingletonSynchronizedTest();
        mInstance.test();
    }
}
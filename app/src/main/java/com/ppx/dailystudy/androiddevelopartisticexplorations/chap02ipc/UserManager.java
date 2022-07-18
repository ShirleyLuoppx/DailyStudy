package com.ppx.dailystudy.androiddevelopartisticexplorations.chap02ipc;

/**
 * @Author: LuoXia
 * @CreateDate: 2022/7/17 23:24
 * @Description: 用于验证开启多进程的时候，不同进程访问同一个变量的时候，如果一个进程里面的这个变量值变了，另一个进程能否实时访问到修改后的值
 * 答案是不能
 */
public class UserManager {
    public static int num = 2;
}

package com.jlhan.basics.multithread.thread;

import com.blankj.utilcode.util.LogUtils;

/**
 * @author hanjinlong
 * @date 2020/6/29
 * @description 1.继承Thread类, 重写run方法
 */
public class TestThread extends Thread {
    @Override
    public void run() {
//        super.run();
//        System.out.println("hello World");
        LogUtils.i("Hello World");
    }
}
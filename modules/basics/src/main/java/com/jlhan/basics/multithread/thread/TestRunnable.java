package com.jlhan.basics.multithread.thread;

import com.blankj.utilcode.util.LogUtils;

/**
 * @author hanjinlong
 * @date 2020/6/29
 * @description 2.实现Runable接口, 并实现该接口的run()方法.
 */
public class TestRunnable implements Runnable {
    @Override
    public void run() {
        LogUtils.i("Hello World");
    }
}
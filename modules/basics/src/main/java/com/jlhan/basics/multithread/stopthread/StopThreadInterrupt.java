package com.jlhan.basics.multithread.stopthread;

import com.blankj.utilcode.util.LogUtils;

/**
 * @author hanjinlong
 * @date 2020/6/29
 * @description 使用interrupt方式结束线程
 */
public class StopThreadInterrupt implements Runnable {

    private long i;

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            i++;
            LogUtils.i("i=" + i);
        }
        LogUtils.i("stop");
    }
}
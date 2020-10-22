package com.jlhan.basics.multithread.stopthread;

import com.blankj.utilcode.util.LogUtils;

/**
 * @author hanjinlong
 * @date 2020/6/29
 * @description 使用开关方式停止线程
 */
public class StopThreadSwitch implements Runnable {

    private long i;
    private volatile boolean on = true;

    @Override
    public void run() {
        while (on) {
            i++;
            LogUtils.i(i);
        }
        LogUtils.i("stop");
    }

    public void cancel() {
        on = false;
    }
}
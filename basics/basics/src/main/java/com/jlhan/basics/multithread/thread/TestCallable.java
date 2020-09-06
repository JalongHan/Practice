package com.jlhan.basics.multithread.thread;

import java.util.concurrent.Callable;

/**
 * @author hanjinlong
 * @date 2020/6/29
 * @description 使用Callable
 */
public class TestCallable implements Callable {
    @Override
    public Object call() throws Exception {
        return "Hello World";
    }
}
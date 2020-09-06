package com.jlhan.basics.multithread.blockqueue;

import com.blankj.utilcode.util.LogUtils;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 使用阻塞队列来实现生产者,消费者模式.
 * 实现更简单了
 */
public class BlockQueueTest {

    private static int queueSize = 10;
    private static ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(queueSize);

    public static class Consumer extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    LogUtils.i("消费数据");
                    queue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static class Producer extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    LogUtils.i("插入数据");
                    queue.put(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

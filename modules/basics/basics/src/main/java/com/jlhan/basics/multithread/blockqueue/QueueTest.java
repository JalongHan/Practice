package com.jlhan.basics.multithread.blockqueue;

import com.blankj.utilcode.util.LogUtils;

import java.util.PriorityQueue;

/**
 * 使用Object.wait(),Object.notify()和非阻塞队列实现生产者,消费者模式
 */
public class QueueTest {
    private static int queueSize = 10;
    private static PriorityQueue<Integer> queue = new PriorityQueue<Integer>(queueSize);

    public static class Consumer extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (queue) {
                    while (queue.size() == 0) {
                        try {
                            LogUtils.i("队列空,等待数据");
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            queue.notify();
                        }
                    }
                    // 每次移走队首元素.
                    queue.poll();
                    queue.notify();
                }
            }
        }
    }

    public static class Producer extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (queue) {
                    while (queue.size() == queueSize) {
                        try {
                            LogUtils.i("队列满,等待有空余空间");
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            queue.notify();
                        }
                    }
                    // 每次插入一个元素
                    queue.offer(1);
                    queue.notify();
                }
            }
        }
    }

}

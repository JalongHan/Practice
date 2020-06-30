package com.jlhan.basics.multithread.reentrantLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Alipay {

    private double[] accounts;
    private Lock aliplaylock;
    private Condition condition;

    public Alipay(int n, double money) {
        aliplaylock = new ReentrantLock();
        // 得到条件对象
        condition = aliplaylock.newCondition();
        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = money;
        }
    }

//    public void transfer(int from, int to, int amount) throws InterruptedException {
//        aliplaylock.lock();
//        try {
//            while (accounts[from] < amount) {
//                // 阻塞当前线程,并放弃锁
//                condition.await();
//            }
//            // 转账的操作
//            accounts[from] = accounts[from] - amount;
//            accounts[to] = accounts[to] + amount;
//            condition.signalAll();
//        } finally {
//            aliplaylock.unlock();
//        }
//    }

    /**
     * 使用synchronized将方法声明来.
     * wait()相当于condition.await(),将一个线程添加到等待集中
     * notifyAll相当于condition.signalAll(),解除等待线程的阻塞状态.
     *
     * @param from
     * @param to
     * @param amount
     * @throws InterruptedException
     */
    public synchronized void transfer(int from, int to, int amount) throws InterruptedException {
        while (accounts[from] < amount) {
            wait();
        }
        // 转账的操作
        accounts[from] = accounts[from] - amount;
        accounts[to] = accounts[to] + amount;
        notifyAll();
    }

    /**
     * 同步代码块是比较脆弱的,通常不推荐使用.一般同步最好用阻塞队列.
     */
//    private final Object lock = new Object();
//    public void transfer(int from, int to, int amount) {
//        synchronized (lock){
//            // 转账操作
//            accounts[from] = accounts[from]-amount;
//            accounts[to] = accounts[to]+amount;
//        }
//    }

}
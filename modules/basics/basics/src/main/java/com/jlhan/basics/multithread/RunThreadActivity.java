package com.jlhan.basics.multithread;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.LogUtils;
import com.jlhan.annotation.ActivityBean;
import com.jlhan.basics.Constants;
import com.jlhan.basics.R;
import com.jlhan.basics.multithread.thread.TestCallable;
import com.jlhan.basics.multithread.thread.TestRunnable;
import com.jlhan.basics.multithread.thread.TestThread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author hanjinlong
 * @date 2020/6/29
 * @description 多线程开启方式
 */
@ActivityBean(activityPath = Constants.MULTI_THREAD.ACTIVITY_PATH, activityName = Constants.MULTI_THREAD.ACTIVITY_NAME)
@Route(path = Constants.MULTI_THREAD.ACTIVITY_PATH)
public class RunThreadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_thread);

        modeOfExtendsThread();
        modeOfImplementsRunnable();
        modeOfCallable();
    }

    /**
     * 3.使用Callable
     */
    private void modeOfCallable() {
        TestCallable callable = new TestCallable();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future future = executorService.submit(callable);
        try {
            LogUtils.i(future.get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 2.实现Runable接口, 并实现该接口的run()方法.
     */
    private void modeOfImplementsRunnable() {
        TestRunnable testRunnable = new TestRunnable();
        Thread thread = new Thread(testRunnable);
        thread.start();
    }

    /**
     * 1.继承Thread类,重写run方法
     */
    private void modeOfExtendsThread() {
        TestThread thread = new TestThread();
        thread.start();
    }
}
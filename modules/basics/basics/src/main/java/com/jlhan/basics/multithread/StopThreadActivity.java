package com.jlhan.basics.multithread;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jlhan.annotation.CollectActivity;
import com.jlhan.basics.Constants;
import com.jlhan.basics.R;
import com.jlhan.basics.multithread.stopthread.StopThreadInterrupt;
import com.jlhan.basics.multithread.stopthread.StopThreadSwitch;

import java.util.concurrent.TimeUnit;

/**
 * @author hanjinlong
 * @date 2020/6/29
 * @description 中断线程
 */
@CollectActivity(activityPath = Constants.STOP_THREAD.ACTIVITY_PATH, activityName = Constants.STOP_THREAD.ACTIVITY_NAME)
@Route(path = Constants.STOP_THREAD.ACTIVITY_PATH)
public class StopThreadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_thread);

        stopByInterrupt();
        stopBySwitch();
    }

    private void stopBySwitch() {
        StopThreadSwitch runnable = new StopThreadSwitch();
        Thread thread = new Thread(runnable);
        thread.start();
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            runnable.cancel();
        }
    }

    private void stopByInterrupt() {
        StopThreadInterrupt runner = new StopThreadInterrupt();
        Thread thread = new Thread(runner);
        thread.start();
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            thread.interrupt();
        }
    }

}
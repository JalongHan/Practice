package com.jlhan.basics.multithread;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jlhan.basics.R;
import com.jlhan.basics.multithread.blockqueue.BlockQueueTest;
import com.jlhan.basics.multithread.blockqueue.QueueTest;

@Route(path = "/BlockingQueue/activity")
public class BlockingQueueActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blocking_queue);

//        commonProducerConsumerStart();
        blcokingProducerConsumerStart();
    }

    private void blcokingProducerConsumerStart() {
        BlockQueueTest.Producer producer = new BlockQueueTest.Producer();
        BlockQueueTest.Consumer consumer = new BlockQueueTest.Consumer();
        producer.start();
        consumer.start();
    }

    private void commonProducerConsumerStart() {
        QueueTest.Producer producer = new QueueTest.Producer();
        QueueTest.Consumer consumer = new QueueTest.Consumer();
        producer.start();
        consumer.start();
    }
}
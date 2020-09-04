package com.jlhan.practice;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jlhan.annotations.BindView;

public class MainActivity extends AppCompatActivity {

    @BindView(value = R.id.tvText)
    private TextView mTvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        mTvText = findViewById(R.id.tvText);
//        mTvText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ARouter.getInstance().build("/view/activity").navigation();
//                ARouter.getInstance().build("/customView/activity").navigation();
//                ARouter.getInstance().build("/customViewGroup/activity").navigation();
//                ARouter.getInstance().build("/MultiThread/activity").navigation();
//                ARouter.getInstance().build("/StopThread/activity").navigation();
//                ARouter.getInstance().build("/BlockingQueue/activity").navigation();
//                ARouter.getInstance().build("/design/activity").navigation();
//                ARouter.getInstance().build("/annotation/activity").navigation();
//            }
//        });
    }
}

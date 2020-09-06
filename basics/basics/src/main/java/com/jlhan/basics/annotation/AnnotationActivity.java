package com.jlhan.basics.annotation;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jlhan.basics.R;
import com.jlhan.basics.annotation.runtime.AnnotationProcessor;

@Route(path = "/annotation/activity")
public class AnnotationActivity extends AppCompatActivity {

    TextView mTvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annotation);
        // 运行时注解处理器
        AnnotationProcessor.invoke();
    }
}
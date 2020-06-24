package com.jlhan.basics.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jlhan.basics.R;

/**
 * @author hanjinlong
 * @date 2020/6/24
 * @description 自定义view
 */
@Route(path = "/customView/activity")
public class CustomViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
    }
}
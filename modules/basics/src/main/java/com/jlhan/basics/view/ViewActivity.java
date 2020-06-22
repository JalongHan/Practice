package com.jlhan.basics.view;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jlhan.basics.R;

@Route(path = "/view/activity")
public class ViewActivity extends AppCompatActivity {

    private View mViewAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        mViewAnim = findViewById(R.id.view_anim);
    }

}

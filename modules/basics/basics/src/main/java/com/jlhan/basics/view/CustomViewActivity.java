package com.jlhan.basics.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jlhan.annotation.CollectActivity;
import com.jlhan.basics.Constants;
import com.jlhan.basics.R;
import com.jlhan.basics.view.custom.TitleBar;

/**
 * @author hanjinlong
 * @date 2020/6/24
 * @description 自定义view
 */
@CollectActivity(activityPath = Constants.CUSTOM_VIEW.ACTIVITY_PATH, activityName = Constants.CUSTOM_VIEW.ACTIVITY_NAME)
@Route(path = Constants.CUSTOM_VIEW.ACTIVITY_PATH)
public class CustomViewActivity extends AppCompatActivity {

    private TitleBar mTitleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
        mTitleBar = findViewById(R.id.title_bar);

        mTitleBar.setLeftListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CustomViewActivity.this, "点击左键", Toast.LENGTH_SHORT).show();
            }
        });
        mTitleBar.setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CustomViewActivity.this, "点击右键", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
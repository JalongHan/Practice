package com.jlhan.basics.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jlhan.annotation.CollectActivity;
import com.jlhan.basics.Constants;
import com.jlhan.basics.R;

@CollectActivity(activityPath = Constants.VIEW.ACTIVITY_PATH, activityName = Constants.VIEW.ACTIVITY_NAME)
@Route(path = Constants.VIEW.ACTIVITY_PATH)
public class ViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
    }

}

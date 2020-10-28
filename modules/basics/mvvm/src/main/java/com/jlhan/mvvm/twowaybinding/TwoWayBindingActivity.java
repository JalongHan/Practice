package com.jlhan.mvvm.twowaybinding;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jlhan.annotation.CollectActivity;
import com.jlhan.mvvm.Constants;
import com.jlhan.mvvm.R;
import com.jlhan.mvvm.databinding.ActivityTwoWayBindingBinding;
import com.jlhan.mvvm.dynamicupdate.ObSwordsman;

@CollectActivity(activityPath = Constants.TWO_WAY_BINDING.ACTIVITY_PATH, activityName = Constants.TWO_WAY_BINDING.ACTIVITY_NAME)
@Route(path = Constants.TWO_WAY_BINDING.ACTIVITY_PATH)
public class TwoWayBindingActivity extends AppCompatActivity {

    private ActivityTwoWayBindingBinding binding;
    private ObSwordsman obSwordsman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_two_way_binding);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_two_way_binding);
        obSwordsman = new ObSwordsman("任我行", "A");
        binding.setObswordsman(obSwordsman);
        binding.btUpdateBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obSwordsman.setName("任我行");
            }
        });

    }
}
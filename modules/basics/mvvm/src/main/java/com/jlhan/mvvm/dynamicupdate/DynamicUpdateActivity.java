package com.jlhan.mvvm.dynamicupdate;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jlhan.annotation.ActivityBean;
import com.jlhan.base.activitybean.Constants;
import com.jlhan.mvvm.R;
import com.jlhan.mvvm.databinding.ActivityDynamicUpdateBinding;

@ActivityBean(activityPath = Constants.DaynmicUpdate.ACTIVITY_PATH, activityName = Constants.DaynmicUpdate.ACTIVITY_NAME)
@Route(path = Constants.DaynmicUpdate.ACTIVITY_PATH)
public class DynamicUpdateActivity extends AppCompatActivity {

    private ObSwordsman obSwordsman;
    private ObFSwordsman obFSwordsman;
    private ActivityDynamicUpdateBinding binding;
    private ObservableArrayList<ObListSwordsman> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_dynamic_update);
        // 使用OBservable更新数据
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dynamic_update);
        obSwordsman = new ObSwordsman("任我行", "A");
        binding.setObswordsman(obSwordsman);
        binding.btUpdataObswordsman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obSwordsman.setName("石破天");
            }
        });
        // 使用OBservableField更新数据
        obFSwordsman = new ObFSwordsman("风清扬", "S");
        binding.setObfswordsman(obFSwordsman);
        binding.btUpdataObfswordsman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obFSwordsman.name.set("令狐冲");
            }
        });
        // 使用Observalbe容器类,
        list = new ObservableArrayList<>();
        final ObListSwordsman obSwordsman1 = new ObListSwordsman("张无忌", "S");
        final ObListSwordsman obSwordsman2 = new ObListSwordsman("周芷若", "B");
        list.add(obSwordsman1);
        list.add(obSwordsman2);
        binding.setListObservable(list);
        binding.btUpdataOblists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obSwordsman1.setName("杨过");
                obSwordsman2.setName("小龙女");
                binding.setListObservable(list);
            }
        });

    }
}
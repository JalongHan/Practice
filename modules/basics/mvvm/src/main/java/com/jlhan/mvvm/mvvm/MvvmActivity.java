package com.jlhan.mvvm.mvvm;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ToastUtils;
import com.jlhan.annotation.CollectActivity;
import com.jlhan.mvvm.ActivityFirst;
import com.jlhan.mvvm.Constants;
import com.jlhan.mvvm.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@CollectActivity(activityPath = Constants.MVVM.ACTIVITY_PATH, activityName = Constants.MVVM.ACTIVITY_NAME)
@Route(path = Constants.MVVM.ACTIVITY_PATH)
public class MvvmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityFirst binding = DataBindingUtil.setContentView(this, R.layout.layout_swordsman);
        Swordsman swordsman = new Swordsman("张无忌", "S");
        binding.setSwordsman(swordsman);

        // 设置点击监听1
        binding.btNext1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShort("点击监听测试1");
            }
        });
        // 设置点击监听2
        binding.setOnclicklistener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShort("点击监听测试2");
            }
        });

        // 变量定义
        binding.setVariableName("风清扬");
        binding.setVariableAge(30);
        binding.setVariableMan(true);

        // 使用List,Map集合
        ArrayList<String> list = new ArrayList<>();
        list.add("0");
        list.add("1");
        binding.setList(list);

        Map<String, String> map = new HashMap<>();
        map.put("age", "30");
        binding.setMap(map);

        String[] arrays = new String[]{"张无忌", "慕容龙城"};
        binding.setArrays(arrays);

        // 调用静态方法
        Swordsman swordsman1 = new Swordsman("独孤求败", "SS");
        binding.setMvvmUtilsSwordsman(swordsman1);
        // Converter 转换器
        binding.setTime(new Date());
    }
}
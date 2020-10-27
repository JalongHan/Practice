package com.jlhan.mvvm.recyclerview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jlhan.annotation.ActivityBean;
import com.jlhan.mvvm.Constants;
import com.jlhan.mvvm.R;
import com.jlhan.mvvm.databinding.ActivityRecyclerviewBinding;
import com.jlhan.mvvm.mvvm.Swordsman;

import java.util.ArrayList;
import java.util.List;

@ActivityBean(activityPath = Constants.RECYCLERVIEW.ACTIVITY_PATH, activityName = Constants.RECYCLERVIEW.ACTIVITY_NAME)
@Route(path = "/activity/recyclerview")
public class RecyclerviewActivity extends AppCompatActivity {

    private ActivityRecyclerviewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_recyclerview);
        // recyclerview用法
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recyclerview);
        initRecyclerview();
    }

    private void initRecyclerview() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recycler.setLayoutManager(layoutManager);
        SwordsmanAdapter adapter = new SwordsmanAdapter(getList());
        binding.recycler.setAdapter(adapter);
    }

    private List<Swordsman> getList() {
        List<Swordsman> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Swordsman swordsman = new Swordsman("张无忌" + i, "SS" + i);
            list.add(swordsman);
        }
        return list;
    }
}
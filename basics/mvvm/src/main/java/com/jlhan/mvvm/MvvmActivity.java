package com.jlhan.mvvm;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jlhan.mvvm.databinding.LayoutSwordsmanBinding;

@Route(path = "/mvvm/activity")
public class MvvmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutSwordsmanBinding binding = DataBindingUtil.setContentView(this, R.layout.layout_swordsman);
        Swordsman swordsman = new Swordsman("张无忌", "S");
        binding.setSwordsman(swordsman);

    }
}
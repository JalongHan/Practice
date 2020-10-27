package com.jlhan.basics.view;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jlhan.annotation.ActivityBean;
import com.jlhan.basics.Constants;
import com.jlhan.basics.R;

@ActivityBean(activityPath = Constants.CUSTON_VIEWGROUP.ACTIVITY_PATH, activityName = Constants.CUSTON_VIEWGROUP.ACTIVITY_NAME)
@Route(path = Constants.CUSTON_VIEWGROUP.ACTIVITY_PATH)
public class CustomViewGroupActivity extends AppCompatActivity {

    private ListView mLvOne;
    private ListView mLvTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_group);

        mLvOne = findViewById(R.id.lv_one);
        mLvTwo = findViewById(R.id.lv_two);
        String[] strs1 = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, strs1);
        mLvOne.setAdapter(adapter1);

        String[] strs2 = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, strs2);
        mLvTwo.setAdapter(adapter2);
    }
}
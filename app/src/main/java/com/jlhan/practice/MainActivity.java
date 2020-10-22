package com.jlhan.practice;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

//    @BindView(value = R.id.tvText)
//    private TextView mTvText;

    private RecyclerView mRvList;
    private LinearLayoutManager mLinearLayoutManager;
    private List<ActivityBean> mList;
    private ActivityAdapter mActivityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRvList = findViewById(R.id.rv_list);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRvList.setLayoutManager(mLinearLayoutManager);
        mActivityAdapter = new ActivityAdapter();
        mRvList.setAdapter(mActivityAdapter);
        paddingData();
        mActivityAdapter.setList(mList);
    }

    private void paddingData() {
        mList = new ArrayList<>();
        ActivityBean bean0 = new ActivityBean();
        bean0.activityName = "View";
        bean0.activityPath = "/view/activity";
        mList.add(bean0);
        ActivityBean bean1 = new ActivityBean();
        bean1.activityName = "自定义View";
        bean1.activityPath = "/customView/activity";
        mList.add(bean1);
        ActivityBean bean2 = new ActivityBean();
        bean2.activityName = "自定义ViewGroup";
        bean2.activityPath = "/customViewGroup/activity";
        mList.add(bean2);
        ActivityBean bean3 = new ActivityBean();
        bean3.activityName = "多线程";
        bean3.activityPath = "/MultiThread/activity";
        mList.add(bean3);
        ActivityBean bean4 = new ActivityBean();
        bean4.activityName = "停止线程";
        bean4.activityPath = "/StopThread/activity";
        mList.add(bean4);
        ActivityBean bean5 = new ActivityBean();
        bean5.activityName = "阻塞队列";
        bean5.activityPath = "/BlockingQueue/activity";
        mList.add(bean5);
        ActivityBean bean6 = new ActivityBean();
        bean6.activityName = "设计模式";
        bean6.activityPath = "/design/activity";
        mList.add(bean6);
        ActivityBean bean7 = new ActivityBean();
        bean7.activityName = "注解";
        bean7.activityPath = "/annotation/activity";
        mList.add(bean7);
        ActivityBean bean8 = new ActivityBean();
        bean8.activityName = "MVVM";
        bean8.activityPath = "/mvvm/activity";
        mList.add(bean8);
        ActivityBean bean9 = new ActivityBean();
        bean9.activityName = "databinding动态更新";
        bean9.activityPath = "/activity/dynamicupdate";
        mList.add(bean9);
        ActivityBean bean10 = new ActivityBean();
        bean10.activityName = "databinding双向绑定";
        bean10.activityPath = "/activity/twowaybinding";
        mList.add(bean10);
        ActivityBean bean11 = new ActivityBean();
        bean11.activityName = "databinding在recyclerview应用";
        bean11.activityPath = "/activity/recyclerview";
        mList.add(bean11);
    }
}

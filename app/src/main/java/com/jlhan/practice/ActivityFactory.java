package com.jlhan.practice;

import java.util.ArrayList;
import java.util.List;

public class ActivityFactory {

    private static List<ActivityBean> mActivityList = new ArrayList<>();

    public static void addActivityBean(String activityName, String activityPath) {
        ActivityBean activityBean = new ActivityBean();
        activityBean.activityName = activityName;
        activityBean.activityPath = activityPath;
        mActivityList.add(activityBean);
    }

}

package com.jlhan.core;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hanjinlong
 * @date 2020/10/22
 * @description 存储activity列表, 有参加编译的都放入一个列表, 方便展示
 */
public class ActivityFactory {

    private static List<ActivityBean> mActivity;

    static {
        mActivity = new ArrayList<>();
    }

    public static List<ActivityBean> getActivityList() {
        return mActivity;
    }

    public static void addActivity(String activityPath, String activityName) {
        ActivityBean bean = new ActivityBean();
        bean.activityPath = activityPath;
        bean.activityName = activityName;
        mActivity.add(bean);
    }

}
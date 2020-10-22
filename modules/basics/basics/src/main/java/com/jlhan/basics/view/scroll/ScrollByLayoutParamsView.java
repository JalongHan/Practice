package com.jlhan.basics.view.scroll;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.blankj.utilcode.util.LogUtils;

/**
 * @author hanjinlong
 * @date 2020/6/22
 * @description View使用LayoutParams滑动
 */
public class ScrollByLayoutParamsView extends View {
    private int lastX;
    private int lastY;

    public ScrollByLayoutParamsView(Context context) {
        super(context);
    }

    public ScrollByLayoutParamsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollByLayoutParamsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 获取手指触摸点的横坐标和纵坐标
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                // 计算移动的距离
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                LogUtils.i("offsetX=" + offsetX, "offsetY=" + offsetY);
                // 使用layoutparams调整view位置
                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) getLayoutParams();
                layoutParams.leftMargin = getLeft() + offsetX;
                layoutParams.topMargin = getTop() + offsetY;
                setLayoutParams(layoutParams);
                break;
        }
        return true;
    }
}

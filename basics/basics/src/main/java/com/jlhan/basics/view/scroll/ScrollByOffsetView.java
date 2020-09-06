package com.jlhan.basics.view.scroll;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author hanjinlong
 * @date 2020/6/22
 * @description View使用offsetLeftAndrRight()与offsetRightAndBottom()方法滑动
 */
public class ScrollByOffsetView extends View {
    private int lastX;
    private int lastY;

    public ScrollByOffsetView(Context context) {
        super(context);
    }

    public ScrollByOffsetView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollByOffsetView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
                // 对left和right进行偏移
                offsetLeftAndRight(offsetX);
                // 对top和bottom进行偏移
                offsetTopAndBottom(offsetY);
                break;
        }
        return true;
    }
}

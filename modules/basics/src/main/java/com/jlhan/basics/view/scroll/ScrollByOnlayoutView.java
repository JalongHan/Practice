package com.jlhan.basics.view.scroll;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author hanjinlong
 * @date 2020/6/22
 * @description View使用onLayout方法滑动
 */
public class ScrollByOnlayoutView extends View {
    private int lastX;
    private int lastY;

    public ScrollByOnlayoutView(Context context) {
        super(context);
    }

    public ScrollByOnlayoutView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollByOnlayoutView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
                // 调用layout方法来重新旋转它的位置
                layout(getLeft() + offsetX, getTop() + offsetY, getRight() + offsetX, getBottom() + offsetY);
                break;
        }
        return true;
    }
}

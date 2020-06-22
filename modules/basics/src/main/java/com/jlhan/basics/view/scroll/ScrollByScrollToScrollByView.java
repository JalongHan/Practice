package com.jlhan.basics.view.scroll;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author hanjinlong
 * @date 2020/6/22
 * @description View使用scrollBy scrollTo方法滑动
 */
public class ScrollByScrollToScrollByView extends View {
    private int lastX;
    private int lastY;

    public ScrollByScrollToScrollByView(Context context) {
        super(context);
    }

    public ScrollByScrollToScrollByView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollByScrollToScrollByView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
                // scrollTo,scrollBy移动的是View的内容,如果在viewGroup中使用,则是移动其所有的子View
                // 方法需要负值才能向我们想要的方向移动
                ((View) getParent()).scrollBy(-offsetX, -offsetY);
                break;
        }
        return true;
    }
}

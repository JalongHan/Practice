package com.jlhan.basics.view.scroll;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Scroller;

import androidx.annotation.Nullable;

/**
 * @author hanjinlong
 * @date 2020/6/22
 * @description View使用Scroller来进行滑动
 */
public class ScrollByScrollerView extends View implements View.OnClickListener {
    private Scroller mScroller;
    private int lastX;
    private int lastY;

    public ScrollByScrollerView(Context context) {
        super(context);
        init(context);
    }

    public ScrollByScrollerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ScrollByScrollerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mScroller = new Scroller(context);
        setOnClickListener(this);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            // 本质 还是用父布局来移动所有子view
            ((View) getParent()).scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }

    public void smoothScrollTo(int destX, int destY) {
        int scrollX = getScrollX();
        int deltaX = destX - scrollX;

        int scrollY = getScrollY();
        int deltaY = destY - scrollY;
        // 这个方法只是将值传进去 在invalidate后会不断回调computeScroll方法
        mScroller.startScroll(scrollX, scrollY, deltaX, deltaY, 2000);
        invalidate();
    }

    @Override
    public void onClick(View v) {
        // 传入负值可正常方向移动
        smoothScrollTo(-400, -200);
    }
}

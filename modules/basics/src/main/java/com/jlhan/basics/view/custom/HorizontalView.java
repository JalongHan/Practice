package com.jlhan.basics.view.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

import com.blankj.utilcode.util.LogUtils;

/**
 * @author hanjinlong
 * @date 2020/6/28
 * @description 自定义ViewGroup
 */
public class HorizontalView extends ViewGroup {

    private int lastInterceptX;
    private int lastInterceptY;
    private int lastX;
    private int lastY;
    private int currentIndex = 0; // 当前子元素
    private int childWidth = 0;
    private Scroller scroller;
    private VelocityTracker tracker;

    public HorizontalView(Context context) {
        super(context);
        init(context);
    }

    public HorizontalView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public HorizontalView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        scroller = new Scroller(context);
        tracker = VelocityTracker.obtain();
        // 需添加clickAble=true,不然接收不到后续的点击事件,接收不到是因为子view没有点击事件.
        // setClickable(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        // 测量所有子view
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        // 如果没有子元素,就设置宽和高都为0
        // TODO: 2020/6/28 应该根据LayoutParams中的宽和高来做相应的处理.根据widthMode和HeightMode分别设置宽高
        //  而且并未考虑padding和子元素的margin
        if (getChildCount() == 0) {
            setMeasuredDimension(0, 0);
        }
        // 宽和高都是AT_MOST,则宽设置为所有子元素宽度的和,高度设置为第一个子元素的高度
        else if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            View childOne = getChildAt(0);
            int childWidth = childOne.getMeasuredWidth();
            int childHeight = childOne.getMeasuredHeight();
            setMeasuredDimension(childWidth * getChildCount(), childHeight);
        }
        // 宽度是AT_MOST 则宽度为所有子元素宽度的和
        else if (widthMode == MeasureSpec.AT_MOST) {
            int childWidth = getChildAt(0).getMeasuredWidth();
            setMeasuredDimension(childWidth * getChildCount(), heightSize);
        }
        // 高度是AT_MOST 则高度为第一个子元素的高度
        else if (heightMode == MeasureSpec.AT_MOST) {
            int childHeight = getChildAt(0).getMeasuredHeight();
            setMeasuredDimension(widthSize, childHeight);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // TODO: 2020/6/28 未处理padding和子元素的margin
        int childCount = getChildCount();
        int left = 0;
        View child;
        for (int i = 0; i < childCount; i++) {
            child = getChildAt(i);
            if (child.getVisibility() != View.GONE) {
                int width = child.getMeasuredWidth();
                childWidth = width;
                child.layout(left, 0, left + width, child.getMeasuredHeight());
                left += width;
            }
        }
    }

    /**
     * 解决onInterceptTouchEvent只能拦截到Down事件
     * MotionEvent.ACTION_MOVE和MotionEvent.ACTION_UP的事件必须要满足
     * 1.onInterceptTouchEvent的返回值为false，因为返回true事件将不会再传递到这个方法里面
     * 2.该ViewGroup必须要有子控件并且该子控件要将传递过去的事件给处理掉（也就是说说return true）
     *
     * @param ev
     * @return boolean 是否拦截事件
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // 在Move时计算每次手指移动的距离,判断是水平还是垂直滑动.
        boolean intercept = false;
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 如果scroller未完成,调用abortAnimation方法打断.
                intercept = false;
                if (!scroller.isFinished()) {
                    scroller.abortAnimation();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - lastInterceptX;
                int deltaY = y - lastInterceptY;
                if (Math.abs(deltaX) - Math.abs(deltaY) > 0) {
                    intercept = true;
                } else {
                    intercept = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                intercept = false;
                break;
        }
        lastX = x;
        lastY = y;
        lastInterceptX = x;
        lastInterceptY = y;
        return intercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtils.i(event.getAction());
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - lastX;
                scrollBy(-deltaX, 0);
                break;
            case MotionEvent.ACTION_UP:
                int distance = getScrollX() - currentIndex * childWidth;
                if (Math.abs(distance) > childWidth / 2) {
                    // 滑动距离 大于宽度的一半就滑动.
                    if (distance > 0) {
                        currentIndex++;
                    } else {
                        currentIndex--;
                    }
                } else {
                    tracker.computeCurrentVelocity(1000);
                    float xV = tracker.getXVelocity();
                    if (Math.abs(xV) > 50) {
                        // 切换到上一个页面
                        currentIndex--;
                    } else {
                        // 切换到下一个页面
                        currentIndex++;
                    }
                }
                currentIndex = currentIndex < 0 ? 0 : currentIndex > getChildCount() - 1 ? getChildCount() - 1 : currentIndex;
                smoothScrollTo(currentIndex * childWidth, 0);
                // 要释放tracker
                tracker.clear();
                break;
        }
        lastX = x;
        lastY = y;
        return super.onTouchEvent(event);
    }

    private void smoothScrollTo(int destX, int destY) {
        LogUtils.i(getScrollX(), getScrollY());
        scroller.startScroll(getScrollX(), getScrollY(), destX - getScrollX(), destY - getScrollY(), 1000);
        invalidate();
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            postInvalidate();
        }
    }
}
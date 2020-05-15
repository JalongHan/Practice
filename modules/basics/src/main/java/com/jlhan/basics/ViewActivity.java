package com.jlhan.basics;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.LogUtils;

import static android.view.GestureDetector.OnDoubleTapListener;
import static android.view.GestureDetector.OnGestureListener;

@Route(path = "/view/activity")
public class ViewActivity extends AppCompatActivity {

    private ScrollTextView mTvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        mTvText = findViewById(R.id.tv_text);
        mTvText.post(new Runnable() {
            @Override
            public void run() {
                // 这些数据值都是基于你布局的
                LogUtils.i("getTop=" + mTvText.getTop(),
                        "getLeft=" + mTvText.getLeft(),
                        "getRight=" + mTvText.getRight(),
                        "getBottom=" + mTvText.getBottom());

                LogUtils.i("getX=" + mTvText.getX(), "getY=" + mTvText.getY(),
                        "getTranslationX=" + mTvText.getTranslationX(), "getTranslationY=" + mTvText.getTranslationY());
            }
        });

//        mTvText.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                // 输出当前滑动的事件类型
//                // ACTION_DOWN 手指刚接触屏幕
//                // ACTION_MOVE 手指在屏幕上移动
//                // ACTION_UP 手指从屏幕上松开的一瞬间
//                LogUtils.i(event.getAction());
//                // 输出点击的位置相对于当前view的xy
//                LogUtils.i("getX=" + event.getX(), "getY=" + event.getY());
//                // 输出点击位置相对于当前屏幕的xy
//                LogUtils.i("getRawX=" + event.getRawX(), "getRawY=" + event.getRawY());
//
//
//                return true;  // 返回true,不然会把事件抛到上层去处理
//            }
//        });

        getWindow().getDecorView().setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // 速度追踪
                VelocityTracker velocityTracker = VelocityTracker.obtain();
                velocityTracker.addMovement(event);
                velocityTracker.computeCurrentVelocity(1000);
                float xVelocity = velocityTracker.getXVelocity();
                float yVelocity = velocityTracker.getYVelocity();
                LogUtils.i("xVelocity=" + xVelocity, "yVelocity=" + yVelocity);
                velocityTracker.clear();
                velocityTracker.recycle();
                // 可以有这些对手势的回调.
                GestureDetector mGestureDetector;
                mGestureDetector = new GestureDetector(ViewActivity.this, new OnGestureListener() {
                    @Override
                    public boolean onDown(MotionEvent e) {
                        return false;
                    }

                    @Override
                    public void onShowPress(MotionEvent e) {

                    }

                    @Override
                    public boolean onSingleTapUp(MotionEvent e) {
                        return false;
                    }

                    @Override
                    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                        return false;
                    }

                    @Override
                    public void onLongPress(MotionEvent e) {

                    }

                    @Override
                    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                        return false;
                    }
                });
                mGestureDetector.setOnDoubleTapListener(new OnDoubleTapListener() {
                    @Override
                    public boolean onSingleTapConfirmed(MotionEvent e) {
                        return false;
                    }

                    @Override
                    public boolean onDoubleTap(MotionEvent e) {
                        return false;
                    }

                    @Override
                    public boolean onDoubleTapEvent(MotionEvent e) {
                        return false;
                    }
                });
                mGestureDetector.setIsLongpressEnabled(false);
                boolean consume = mGestureDetector.onTouchEvent(event);

                return consume;
            }
        });

        mTvText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mTvText.smoothScrollTo(100, 0);
                mTvText.scrollTo(-100, 0);
            }
        });


    }


}

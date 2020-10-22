package com.jlhan.basics.view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.jlhan.basics.R;

public class RectView extends View {

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int mColor = Color.RED;

    public RectView(Context context) {
        super(context);
        initDraw();
    }

    public RectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.RectView);
        // 提取RectView属性集合的rect_color属性.如果没设置,默认值为Color.RED
        mColor = mTypedArray.getColor(R.styleable.RectView_rect_color, Color.RED);
        // 获取资源后要及时回收
        mTypedArray.recycle();
        initDraw();
    }

    public RectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDraw();
    }

    private void initDraw() {
        mPaint.setColor(mColor);
        mPaint.setStrokeWidth(1.5f);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(600, 600);
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(600, heightSpecSize);
        } else if (heightMeasureSpec == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSpecSize, 600);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();


        int width = getWidth() - paddingLeft - paddingRight;
        int height = getHeight() - paddingTop - paddingBottom;
        canvas.drawRect(paddingLeft, paddingTop, width + paddingRight, height + paddingBottom, mPaint);
    }
}

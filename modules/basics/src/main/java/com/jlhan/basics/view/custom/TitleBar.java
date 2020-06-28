package com.jlhan.basics.view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jlhan.basics.R;

/**
 * @author hanjinlong
 * @date 2020/6/28
 * @description 自定义组合控件, titleBar
 */
public class TitleBar extends RelativeLayout {

    private ImageView mTitleBarLeft;
    private TextView mTitleBarTitle;
    private ImageView mTitleBarRight;
    private RelativeLayout mRootLayout;

    private int mColor = Color.BLUE;
    private int mTextColor = Color.WHITE;
    private String mTitleName;

    public TitleBar(Context context) {
        super(context);
        initView(context);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 获取自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);
        mColor = typedArray.getColor(R.styleable.TitleBar_title_bg, Color.BLUE);
        mTextColor = typedArray.getColor(R.styleable.TitleBar_title_text_color, Color.RED);
        mTitleName = typedArray.getString(R.styleable.TitleBar_title_text);
        typedArray.recycle();
        initView(context);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_custom_title, this, true);
        mTitleBarLeft = findViewById(R.id.iv_titlebar_left);
        mTitleBarTitle = findViewById(R.id.tv_titlebar_title);
        mTitleBarRight = findViewById(R.id.iv_titlebar_right);
        mRootLayout = findViewById(R.id.layout_titlebar_rootlayout);
        // 设置背景颜色
        mRootLayout.setBackgroundColor(mColor);
        // 设置标题文字颜色
        mTitleBarTitle.setTextColor(mTextColor);
    }

    public void setTitle(String titleName) {
        if (!TextUtils.isEmpty(titleName)) {
            mTitleBarTitle.setText(titleName);
        }
    }

    public void setLeftListener(OnClickListener onClickListener) {
        mTitleBarLeft.setOnClickListener(onClickListener);
    }

    public void setRightListener(OnClickListener onClickListener) {
        mTitleBarRight.setOnClickListener(onClickListener);
    }

}

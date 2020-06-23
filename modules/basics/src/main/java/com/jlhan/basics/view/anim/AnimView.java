package com.jlhan.basics.view.anim;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.jlhan.basics.R;

/**
 * @author hanjinlong
 * @date 2020/6/22
 * @description 此view中有些动画例子
 */
public class AnimView extends View implements View.OnClickListener {

    public AnimView(Context context) {
        super(context);
        init(context);
    }

    public AnimView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AnimView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
//        startTranslationXAnim();
//        setValueAnimator();
//        setAnimListener();
        setAnimSet();
//        setPropertyValuesHolder();
//        setAnimXml();
    }

    // 6.用xml方式使用属性动画
    private void setAnimXml() {
        Animator animator = AnimatorInflater.loadAnimator(getContext(), R.animator.scale);
        animator.setTarget(this);
        animator.start();
    }

    // 5.组合动画PropertyValuesHolder 这个动画没有AnimatorSet丰富
    private void setPropertyValuesHolder() {
        PropertyValuesHolder valuesHolder1 = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 1.5f);
        PropertyValuesHolder valuesHolder2 = PropertyValuesHolder.ofFloat("rotationX", 0.0f, 90.0f, 1.0f);
        PropertyValuesHolder valuesHolder3 = PropertyValuesHolder.ofFloat("alpha", 1.0f, 0.3f, 1.0f);
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(this, valuesHolder1, valuesHolder2, valuesHolder3);
        objectAnimator.setDuration(2000).start();
    }

    // 4.组合动画 AnimatorSet
    private void setAnimSet() {
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(this, "translationX", 0.0f, 200, 0.0f, 0.0f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(this, "scaleX", 1.0f, 2.0f);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(this, "rotationX", 0.0f, 90.0f, 0.0f);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(1000);
        set.play(animator1).with(animator2).after(animator3);
        set.start();
    }

    // 3.动画的监听
    private void setAnimListener() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, "alpha", 1.5f);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        // 也可以使用AnimatorListenerAdapter来选择必要的监听
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                super.onAnimationRepeat(animation);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }

            @Override
            public void onAnimationPause(Animator animation) {
                super.onAnimationPause(animation);
            }

            @Override
            public void onAnimationResume(Animator animation) {
                super.onAnimationResume(animation);
            }
        });
    }

    // 2.ValueAnimator 只有数值返回
    private void setValueAnimator() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 100);
        valueAnimator.setTarget(this);
        valueAnimator.setDuration(1000);
        valueAnimator.start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float nFloat = (float) valueAnimator.getAnimatedValue();
            }
        });
    }

    // 1.ObjectAnimator
    private void startTranslationXAnim() {
        // 需要操作的方法必须要有get和set方法，如果没有，可以定义包装类来增加getset方法，也可以不是继承View
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(this, "translationX", 200);
        objectAnimator.setDuration(200);
        objectAnimator.start();
    }
}

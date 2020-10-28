package com.jlhan.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * @author hanjinlong
 * @date 2020/10/28
 * @description 用来收集activity的路径和名字的注解
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface CollectActivity {

    String activityPath();

    String activityName();

}
package com.jlhan.basics.annotation.runtime;

import com.blankj.utilcode.util.LogUtils;

import java.lang.reflect.Method;

public class AnnotationProcessor {
    public static void invoke() {
        Method[] methods = RuntimeAnnotationTest.class.getDeclaredMethods();
        for (Method m : methods) {
            GET get = m.getAnnotation(GET.class);
            LogUtils.i(get.value());
        }
    }
}
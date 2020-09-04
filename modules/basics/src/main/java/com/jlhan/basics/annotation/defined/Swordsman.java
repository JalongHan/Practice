package com.jlhan.basics.annotation.defined;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Swordsman {
    String name() default "张无忌";

    int age() default 23;
}

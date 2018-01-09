package com.simple.validate.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,ElementType.PARAMETER})
public @interface RanageLength {

    String value() default "";
    /*
      include max value
     */
    int  max() default 0;

    /*
      include min value
     */
    int min() default 0;

    int code() default 0;

    String message() default "字段长度不能超过%s,小于%s";
}

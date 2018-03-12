package com.twp.common.component.process.statemachine.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MapFunctionListener {
    /*
    标识
     */
    String id() default "";

    /*
    名称描述
     */
    String name() default "";

    /*
    具体map key
     */
    String code() default "";

    /*
    key 分组
     */
    String group() default "";


}

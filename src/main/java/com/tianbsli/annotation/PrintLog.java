package com.tianbsli.annotation;

import java.lang.annotation.*;

/**
 * @author Jorsh
 * @version 1.0
 * @date 2020/3/22 10:36 下午
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PrintLog {
}

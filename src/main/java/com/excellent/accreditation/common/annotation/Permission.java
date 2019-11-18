package com.excellent.accreditation.common.annotation;

import com.excellent.accreditation.common.domain.Const;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Permission {
    String[] roles() default {Const.ADMIN, Const.TEACHER, Const.STUDENT};
}

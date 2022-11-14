package org.example.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})         // 클래스에 어노테이션을 붙이고 싶기 때문에 대상을 TYPE으로 지정함.
@Retention(RetentionPolicy.RUNTIME) // 유지 기간은 런타임 기간까지
public @interface Controller {
}

package org.example.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})     // RequestMapping은 클래스와 메소드에 붙일 수 있다.
@Retention(RetentionPolicy.RUNTIME)                 // 유지 기간은 런타임 기간까지
public @interface RequestMapping {

    String value() default "";

    // GET, POST 등 메소드에 따라 RequestMethod를 요청할 수 있고, GET 요청에 URL Path를 입력할 수 있기 때문에 URL Path에 해당하는 value와 GET 요청인지 POST 요청인지에 따른 메소드 설정
    ReqeustMethod[] method() default {};
}

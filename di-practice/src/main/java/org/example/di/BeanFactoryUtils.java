package org.example.di;

import org.example.annotation.Inject;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Constructor;
import java.util.Set;

public class BeanFactoryUtils {

    public static Constructor<?> getInjectedConstructor(Class<?> clazz) {
        // 클래스 타입 객체에 모든 생성자를 가져온다. 단, Inject 어노테이션이 붙은 생성자에 한해서
        Set<Constructor> injectedConstructors = ReflectionUtils.getAllConstructors(clazz, ReflectionUtils.withAnnotation(Inject.class));

        if(injectedConstructors.isEmpty()){
            return null;
        }

        // 일단 첫번째 요소만 반환
        return injectedConstructors.iterator().next();
    }
}

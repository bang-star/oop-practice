package org.example.di;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BeanFactory {

    private final Set<Class<?>> preInstantiatedClazz;

    // [Key] 클래스 타입 객체, [Value] 클래스 타입 객체의 인스턴스
    private Map<Class<?>, Object> beans = new HashMap<>();

    public BeanFactory(Set<Class<?>> preInstantiatedClazz) {
        this.preInstantiatedClazz = preInstantiatedClazz;
        initialize();
    }

    // 어떤 타입의 파라미터가 인자로 들어올지 알수 없기 때문에 제네릭 타입으로 선언
    public <T> T getBean(Class<T> requiredType) {
        // 해당 인스턴스 변수에서 요구된 클래스 타입 객체의 인스턴스를 반환
        return (T) beans.get(requiredType);
    }
}

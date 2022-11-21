package org.example.di;

import org.example.annotation.Inject;
import org.reflections.ReflectionUtils;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class BeanFactory {

    private final Set<Class<?>> preInstantiatedClazz;

    // [Key] 클래스 타입 객체, [Value] 클래스 타입 객체의 인스턴스
    private Map<Class<?>, Object> beans = new HashMap<>();

    public BeanFactory(Set<Class<?>> preInstantiatedClazz) {
        this.preInstantiatedClazz = preInstantiatedClazz;
        initialize();
    }

    private void initialize() {
        for (Class<?> clazz : preInstantiatedClazz) {
            Object instance = createInstance(clazz);
            beans.put(clazz, instance);
        }
    }

    private Object createInstance(Class<?> clazz) {
        // 생성자
        Constructor<?> constructor = findConstructor(clazz);

        // 파라미터 정보
        List<Object> parameters = new ArrayList<>();
        for (Class<?> typeClass : constructor.getParameterTypes()) {
            // 파라미터 타입에 대한 클래스 타입 객체를 가져와서 클래스 타입 객체에 대한 인스턴스를 가져오는 메소드이다.
            parameters.add(getParameterByClass(typeClass));
        }

        // 인스턴스 생성
        try {
            return constructor.newInstance(parameters.toArray());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private Constructor<?> findConstructor(Class<?> clazz) {
        Constructor<?> constructor = BeanFactoryUtils.getInjectedConstructor(clazz);

        if (Objects.nonNull(constructor)) {
            return constructor;
        }

        return clazz.getConstructors()[0];
    }


    // 어떤 타입의 파라미터가 인자로 들어올지 알수 없기 때문에 제네릭 타입으로 선언
    public <T> T getBean(Class<T> requiredType) {
        // 해당 인스턴스 변수에서 요구된 클래스 타입 객체의 인스턴스를 반환
        return (T) beans.get(requiredType);
    }
}

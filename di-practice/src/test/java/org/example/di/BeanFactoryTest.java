package org.example.di;

import org.example.annotation.Controller;
import org.example.annotation.Service;
import org.example.controller.UserController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class BeanFactoryTest {

    private Reflections reflections;
    private BeanFactory beanFactory;

    // Top-Down 방식 - 아직 만들지 않은 메소드이지만 먼저 정의를 하고 구현하는 방식
    @BeforeEach
    void setUp() {
        reflections = new Reflections("org.example");
        // getTypesAnnotatedWith은 annotation을 파라미터로 받아서 어노테이션이 붙은 클래스 타입 객체를 리턴해주는 메소드이다. (TOP)
        Set<Class<?>> preInstantiatedClazz = getTypesAnnotatedWith(Controller.class, Service.class);        // UserController, UserService

        beanFactory = new BeanFactory(preInstantiatedClazz);

    }


    private Set<Class<?>> getTypesAnnotatedWith(Class<? extends Annotation>... annotations) { // 인자의 개수를 알 수 없기 때문에 자바 문법으로 표현

        Set<Class<?>> beans = new HashSet<>();
        for (Class<? extends Annotation> annotation : annotations) {
            // 해당 annotation 타입이 붙은 객체 정보를 조회하여 beans 에 추가
            beans.addAll(reflections.getTypesAnnotatedWith(annotation));
        }

        return beans;
    }

    @Test
    void diTest() {
        UserController userController = beanFactory.getBean(UserController.class);

        assertThat(userController).isNotNull();
        assertThat(userController.getUserService()).isNotNull();
    }
}
package org.example;

import org.example.annotation.Controller;
import org.example.annotation.Service;
import org.example.model.User;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


/**
 *  @ Controller 애노테이션이 설정돼 있는 모든 클래스를 찾아서 출력한다.
 */

public class ReflectionTest {

    private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);

    @Test
    void controllerScan() {
        Set<Class<?>> beans = getTypesAnnotatedWith(List.of(Controller.class, Service.class));

        logger.debug("beans: [{}]", beans);
    }

    // model 테스트를 위한 코드
    @Test
    void showClass() {
        Class<User> clazz = User.class;
        // 클래스 이름 - org.example.model.User
        logger.debug(clazz.getName());

        // 클래스 필드 정보 - [private java.lang.String org.example.model.User.userId, private java.lang.String org.example.model.User.name]
        logger.debug("User all declared fields: [{}]", Arrays.stream(clazz.getDeclaredFields()).collect(Collectors.toList()));

        // 생성자 정보 - [public org.example.model.User(java.lang.String,java.lang.String)]
        logger.debug("User all declared constructors: [{}]", Arrays.stream(clazz.getDeclaredConstructors()).collect(Collectors.toList()));

        // 메소드 정보 - [public boolean org.example.model.User.equals(java.lang.Object), public int org.example.model.User.hashCode(), public boolean org.example.model.User.equalsUser(org.example.model.User)]
        logger.debug("User all declared methods: [{}]", Arrays.stream(clazz.getDeclaredMethods()).collect(Collectors.toList()));
    }

    // 힙 영역에 로드돼 있는 클래스 타입의 객체를 가져오는 방법 3가지
    @Test
    void load() throws ClassNotFoundException {
        // 방법 1
        Class<User> clazz = User.class;

        // 방법 2
        User user = new User("wizard", "name");
        Class<? extends User> clazz2 = user.getClass();

        // 방법 3
        Class<?> clazz3 = Class.forName("org.example.model.User");

        // 동일성 체크
        logger.debug("clazz: [{}]", clazz);
        logger.debug("clazz2: [{}]", clazz2);
        logger.debug("clazz3: [{}]", clazz3);

        assertThat(clazz == clazz2).isTrue();
        assertThat(clazz2 == clazz3).isTrue();
        assertThat(clazz3 == clazz).isTrue();
    }

    private Set<Class<?>> getTypesAnnotatedWith(List<Class<? extends Annotation>> annotations) {
        // 해당 패키지 밑에 있는 클래스에 대해서
        Reflections reflections = new Reflections("org.example");

        Set<Class<?>> beans = new HashSet<>();
        // "Controller"라는 어노테이션이 붙은 클래스를 찾는다.

        // AS-IS
        // beans.addAll(reflections.getTypesAnnotatedWith(Controller.class));
        // beans.addAll(reflections.getTypesAnnotatedWith(Service.class));

        // TO-BE
        annotations.forEach(annotation -> beans.addAll(reflections.getTypesAnnotatedWith(annotation)));

        return beans;
    }
}


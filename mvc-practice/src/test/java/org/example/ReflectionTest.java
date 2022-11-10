package org.example;

import org.example.annotation.Controller;
import org.example.annotation.Service;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;


/**
 *  @ Controller 애노테이션이 설정돼 있는 모든 클래스를 찾아서 출력한다.
 */

public class ReflectionTest {

    private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);

    @Test
    void controllerScan() {
        // 해당 패키지 밑에 있는 클래스에 대해서
        Reflections reflections = new Reflections("org.example");

        Set<Class<?>> beans = new HashSet<>();
        // "Controller"라는 어노테이션이 붙은 클래스를 찾는다.

        // AS-IS
        beans.addAll(reflections.getTypesAnnotatedWith(Controller.class));
        beans.addAll(reflections.getTypesAnnotatedWith(Service.class));

        logger.debug("beans: [{}]", beans);
    }
}

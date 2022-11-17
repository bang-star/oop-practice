package org.example.di;

import org.junit.jupiter.api.BeforeEach;
import org.reflections.Reflections;

class BeanFactoryTest {

    private Reflections reflections;
    private BeanFactory beanFactory;

    @BeforeEach
    void setUp() {
        reflections = new Reflections("org.example");
        beanFactory = new BeanFactory();
    }
}
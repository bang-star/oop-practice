package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CookingTest {

    @DisplayName("메뉴에 해당하는 음식을 만든다.")
    @Test
    void makeCookTest() {
        // 1. 요리사 클래스 생성
        Cooking cooking = new Cooking();

        // 2. 메뉴를 선정하기
        MenuItem menuItem = new MenuItem("돈까스", 5000);

        // 3. 요리를 만들라고 요청하면 요리를 만들어줄 것이다.
        Cook cook = cooking.makeCook(menuItem);

        assertThat(cook).isEqualTo(new Cook("돈까스", 5000));
    }
}

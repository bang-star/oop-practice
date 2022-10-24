package org.example;

/*
* 간단한 사칙연산을 할 수 있다.
* 양수로만 계산할 수 있다.
* 나눗셈에서 0을 나누는 경우 illegalArgument 예외를 발생시킨다.
* MVC 패턴(Model-View-Controller) 기반으로 구현한다.
* */

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculatorTest {

    // 피연산자, 연산자, 피연산자를 Calculator에 전달하여 그에 대한 결과값을 반환받는다.
    @DisplayName("간단한 사칙연산을 할 수 있다.")
    @ParameterizedTest
    @MethodSource("formulaAndResult")
    void additionTest(int operand1, String operator, int operand2, int result) {
        int calculateResult = Calculator.calculate(operand1, operator, operand2);
        assertThat(result).isEqualTo(calculateResult);
    }

    private static Stream<Arguments> formulaAndResult(){
        return Stream.of(
                Arguments.arguments(1, "+", 2, 3),
                Arguments.arguments(2, "-", 3, -1),
                Arguments.arguments(2, "*", 3, 6),
                Arguments.arguments(4, "/", 2, 2)
        );
    }
}

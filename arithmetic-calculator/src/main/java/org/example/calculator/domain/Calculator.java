package org.example.calculator.domain;

import org.example.calculator.tobe.*;

import java.util.List;

public class Calculator {

    // 인터페이스의 구현체들을 상위 인터페이스 타입에 리스트를 통해 저장시킨다.
    private static final List<ArithmeticOperator> arithmeticOperators = List.of(new AdditionOperator(), new SubtractionOperator(), new MultiplicationOperator(), new DivisionOperator());

    public static int calculate(PositiveNumber operand1, String operator, PositiveNumber operand2) {
        return arithmeticOperators.stream()
                // 1. 해당하는 연산자에 구현체를 찾는다.
                .filter(arithmeticOperator -> arithmeticOperator.supports(operator))
                // 2. 해당 구현체의 calculate 메소드에 위임한다.
                .map(arithmeticOperator -> arithmeticOperator.calculate(operand1, operand2))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바른 사칙연산이 아닙니다."));
    }
}

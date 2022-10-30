package org.example.calculator.tobe;

import org.example.calculator.domain.PositiveNumber;

public interface ArithmeticOperator {

    // 1. 해당 연산자에 대한 서비스를 지원하는지
    boolean supports(String operator);

    // 2. 사칙연산을 처리
    int calculate(PositiveNumber operand1, PositiveNumber operand2);
}

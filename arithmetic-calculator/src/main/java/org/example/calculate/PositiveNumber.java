package org.example.calculate;

public class PositiveNumber {
    private final int value;

    public PositiveNumber(int value) {
        validate(value);
        this.value = value;
    }

    private void validate(int value) {
        if(isNegativeNumber(value))
            throw new IllegalArgumentException("0 또는 음수를 전달할 수 없습니다.");
    }

    private boolean isNegativeNumber(int value) {
        return value <= 0;
    }

    // 계산은 int 값으로 반환해주기 위한 메소드
    public int toInt(){
        return value;
    }
}

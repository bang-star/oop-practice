package org.example;

import java.util.List;

/*
 * 요구사항
 * 평균학점 = (학점 수 x 교과목 평점)의 합계 / 수강신청 총학점 수
 * 일급 컬렉션 사용
 * */

public class GradeCalculator {

    private final Courses courses;

    public GradeCalculator(List<Course> courses) {
        this.courses = new Courses(courses);
    }

    public GradeCalculator(Courses courses) {
        this.courses = courses;
    }

    // 평균학점 = (학점 수 x 교과목 평점)의 합계 / 수강신청 총학점 수
    public double calculateGrade() {

        // (학점 수 x 교과목 평점)의 합계
        double multipliedCreditAndCourseGrade = courses.multiplyCreditAndCourseGrade();

        // 수강신청 총학점 수
        int totalCompletedCredit = courses.calculateTotalCompletedCredit();

        return multipliedCreditAndCourseGrade / totalCompletedCredit;
    }
}

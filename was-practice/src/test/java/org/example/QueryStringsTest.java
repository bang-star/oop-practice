package org.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class QueryStringsTest {

    @Test
    void createTest() {
        // 일급 컬렉션을 이용한 방법
        QueryStrings queryStrings = new QueryStrings("operand1=11&operator=*&operand2=55");     // List<QueryString>

        assertThat(queryStrings).isNotNull();
    }
}

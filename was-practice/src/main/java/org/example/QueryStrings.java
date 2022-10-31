package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueryStrings {

    // 일급 컬렉션
    private List<QueryString> queryStrings = new ArrayList<>();

    // operand1=11 & operator=* & operand2=55
    public QueryStrings(String queryStringLine) {
        String[] queryStringTokens = queryStringLine.split("&");

        Arrays.stream(queryStringTokens)
                .forEach(queryString -> {
                    // Key와 Value로 분리리
                   String[] values = queryString.split("=");
                   if(values.length != 2){
                       throw new IllegalArgumentException("잘못된 QueryString 포맷을 가진 문자열입니다.");
                   }

                    queryStrings.add(new QueryString(values[0], values[1]));
                });
    }


    public String getValue(String key) {
        return this.queryStrings.stream()
                .filter(queryString -> queryString.exists(key))
                .map(QueryString::getValue)
                .findFirst()
                .orElseThrow(null);
    }
}

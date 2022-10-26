package org.example;

public class Customer {

    public void order(String name, Menu menu, Cooking cooking){
        // 메뉴판에서 메뉴를 선택한다.
        MenuItem menuItem = menu.choose(name);
        Cook cook = cooking.makeCook(menuItem);
    }
}

package org.example;

public class Cooking {

    public Cook makeCook(MenuItem menuItem) {
        // AS-IS
        // return new Cook("돈까스", 5000);

        // TO-BE
        Cook cook = new Cook(menuItem);
        return cook;
    }
}

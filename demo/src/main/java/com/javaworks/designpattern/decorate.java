package com.javaworks.designpattern;

/**
 * Created by Intellij IDEA.
 * User:  penglong.huang
 * Date:  2022/9/10
 */
public class decorate {

    public static void main(String[] args) {
        Drink milk = new Milk();
        milk = new whiteSugar(milk);
        milk.drink();
    }
}

class Milk implements Drink {
    private Drink drink;

    @Override
    public void drink() {
        System.out.println("milk");
    }
}

class Sugar implements Drink {

    @Override
    public void drink() {
        System.out.println("sugar");
    }
}

class whiteSugar extends Sugar {
    private Drink drink;

    whiteSugar(Drink drink) {
        super();
        this.drink = drink;
    }

    @Override
    public void drink() {
        System.out.println("white sugar");
        drink.drink();
    }
}


interface Drink {
    void drink();
}

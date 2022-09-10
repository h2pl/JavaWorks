package com.javaworks.designpattern;

/**
 * Created by Intellij IDEA.
 * User:  penglong.huang
 * Date:  2022/9/10
 */
public class Adapter {

    public static void main(String[] args) {
        OurFriend friend = new CatFriend();
        OurFriend dogFriend = new DogFriend();
        friend.speak();
        dogFriend.speak();

        IAnimal dog = new Dog();
        // 一只猫
        IAnimal cat = new Cat();
        // 万物拟人
        new AnimalFriendAdaper(dog).speak();
        new AnimalFriendAdaper(cat).speak();
    }
}

class Cat implements IAnimal {
    @Override
    public void makeSound() {
        System.out.println("猫猫:喵喵喵。。。。。。。。。。。。。");
    }
}

class Dog implements IAnimal {
    @Override
    public void makeSound() {
        System.out.println("狗：汪汪汪汪。。。。。。。。。");
    }
}

interface IAnimal {
    void makeSound();
}

class AnimalFriendAdaper implements OurFriend {
    private IAnimal animal;

    public AnimalFriendAdaper(IAnimal animal) {
        this.animal = animal;
    }

    @Override
    public void speak() {
        animal.makeSound();
    }
}

class DogFriend extends Dog implements OurFriend {
    @Override
    public void speak() {
        super.makeSound();
    }
}

interface OurFriend {
    void speak();
}

class CatFriend extends Cat implements OurFriend {
    @Override
    public void speak() {
        super.makeSound();
    }
}

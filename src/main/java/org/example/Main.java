package org.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {
    /**
     * Необходимо создать класс Box, описывающий коробку с фруктами. В одной коробке должны быть фрукты только одного типа.
     * У коробки должны быть методы:
     * 1. Метод добавления нового фрукта в коробку add(Fruit fruit)
     * 2. Метод подсчета суммарного веса коробки getWeight()
     * 3. Метод пересыпания фруктов одной коробки в другую move(Box<T> box)
     * 4. * Реализовать итератор на коробке. То есть должно быть доступно написать foreach, который итерируется по фруктам в коробке.
     */
    public static void main(String[] args) {
        Box<Apple> appleBox = new Box<>();
        System.out.println(appleBox.getWeight()); // 0
        appleBox.add(new Apple(2));
        appleBox.add(new Apple(4));
        System.out.println(appleBox.getWeight()); // 6
//        appleBox.add(new Orange(4));
        appleBox.add(new GoldenApple(4));
        System.out.println(appleBox.getWeight()); // 10

        Box<Orange> orangeBox = new Box<>();
//        orangeBox.add(new Apple(2));
        orangeBox.add(new Orange(8));
        System.out.println(orangeBox.getWeight()); // 8

//        orangeBox.move(appleBox);
//        appleBox.move(orangeBox);

        Box<GoldenApple> goldenAppleBox = new Box<>();
        goldenAppleBox.add(new GoldenApple(20));
//        goldenAppleBox.add(new Apple(20));
//        appleBox.move(goldenAppleBox);
        goldenAppleBox.move(appleBox);

        System.out.println(goldenAppleBox.getWeight()); // 0
        System.out.println(appleBox.getWeight()); // 30

        for (Apple apple : appleBox) {
            System.out.println("Яблоко: " + apple);
        }

        for (GoldenApple goldenApple : goldenAppleBox) {
            System.out.println("Golden Apple: " + goldenApple);
        }

        for (Orange orange : orangeBox) {
            System.out.println("Апельсин: " + orange);
        }
    }

    static class Box<T extends Fruit> implements Iterable<T> {
        private List<T> fruits;

        public Box() {
            fruits = new ArrayList<>();
        }

        public void add(T fruit) {
            fruits.add(fruit);
        }

        public int getWeight() {
            int totalWeight = 0;
            for (T fruit : fruits) {
                totalWeight += fruit.getWeight();
            }
            return totalWeight;
        }

        public void move(Box<? super T> another) {
            another.fruits.addAll(this.fruits);
            this.fruits.clear();
        }

        @Override
        public Iterator<T> iterator() {
            return fruits.iterator();
        }
    }

    static class Fruit {
        private int weight;

        public Fruit(int weight) {
            this.weight = weight;
        }

        public int getWeight() {
            return weight;
        }
    }

    static class Apple extends Fruit {
        public Apple(int weight) {
            super(weight);
        }
    }

    static class GoldenApple extends Apple {
        public GoldenApple(int weight) {
            super(weight);
        }
    }

    static class Orange extends Fruit {
        public Orange(int weight) {
            super(weight);
        }
    }
}
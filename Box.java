/*
3. Большая задача:
a. Есть классы Fruit -> Apple, Orange;(больше фруктов не надо)
b. Класс Box в который можно складывать фрукты, коробки условно сортируются по типу фрукта,
   поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
c. Для хранения фруктов внутри коробки можете использовать ArrayList;
d. Сделать метод getWeight() который высчитывает вес коробки, зная количество фруктов и вес одного фрукта
   (вес яблока - 1.0f, апельсина - 1.5f, не важно в каких это единицах);
e. Внутри класса коробка сделать метод compare, который позволяет сравнить текущую коробку с той, которую подадут в compare
   в качестве параметра, true - если их веса равны, false в противном случае
   (коробки с яблоками мы можем сравнивать с коробками с апельсинами);
f. Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую коробку
   (помним про сортировку фруктов, нельзя яблоки высыпать в коробку с апельсинами),
   соответственно в текущей коробке фруктов не остается, а в другую перекидываются объекты, которые были в этой коробке;
g. Не забываем про метод добавления фрукта в коробку.
*/

package ru.geekbrains.lesson1.homework;

import java.util.ArrayList;

public class Box {
    private ArrayList<Fruit> array;

    private int size = 0;
    private int capacity = 20;
    private float weight = 0.0f;
    private String type = "Apple";

    public Box(int capacity, String type) {
        this.capacity = capacity;
        this.type = type;
        this.array = new ArrayList<>();
    }

    public static void main(String[] args) {
        Box box1, box2, box3;
        box1 = new Box(5, "Apple");
        box2 = new Box(10, "Apple");
        box3 = new Box(10, "Orange");

        Apple app = new Apple();
        for (int i = 0; i < 5; i++)
            box1.addApple(app);
        for (int i = 0; i < 10; i++)
            box2.addApple(app);
        Orange ora = new Orange();
        for (int i = 0; i < 10; i++)
            box3.addOrange(ora);

        System.out.println("Начало программы.");
        float w;
        System.out.print("Коробка 1: ");
        w = box1.getBoxWeight();
        System.out.println("\nСравнение двух коробок: ");
        if (!box1.compare(box2)) {
            System.out.println("Веса двух коробок не совпали.");
        } else {
            System.out.println("Веса двух коробок совпадают.");
        }
        System.out.println("\nКопирование фруктов: ");
        for (int i = 0; i < 3; i++) {
            if (box1.moveFruits(box1, box2, i)) {
                System.out.println("Фрукты скопированы.");
            }
        }
        System.out.println("Конец программы.");
    }

    public void addApple(Apple apple) {
        if (this.type == "Apple" || size == 0) {
            apple = new Apple();
            this.size += 1;
            this.type = "Apple";
            this.array.add(apple);
        }
    }

    public void addOrange(Orange orange) {
        if (this.type == "Orange" || size == 0) {
            orange = new Orange();
            this.size += 1;
            this.type = "Orange";
            this.array.add(orange);
        }
    }

    public void deleteApple(int index) {
        array.remove(index);
    }

    public void deleteOrange(int index) {
        array.remove(index);
    }

    public float getBoxWeight() {
        float weight = 0;
        /*for (Fruit fruit : array ) {
            weight += fruit.getWeight();
        }*/

        if (this.type == "Apple") {
            weight = this.size * Apple.APPLE_WEIGHT;
        } else {
            weight = this.size * Orange.ORANGE_WEIGHT;
        }

        System.out.println("Вес коробки: " + weight);
        return weight;
    }

    public boolean compare(Box box) {
        return (this.getBoxWeight() == box.getBoxWeight());
    }

    public boolean moveFruits(Box box1, Box box2, int index) {
        if (box1.type == box2.type) {
            Box box = new Box(10, box1.type);
            box2.array.ensureCapacity(100);
            box.array.add(box1.array.get(index));
            box1.array.remove(index);
            box2.array.set(index, box.array.get(0));

            box1.size -= 1;
            box2.size += 1;
            return true;
        } else {
            return false;
        }
    }
}
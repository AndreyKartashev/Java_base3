/*      1. Написать метод, который меняет два элемента массива местами.
           (массив может быть любого ссылочного типа);
        2. Написать метод, который преобразует массив в ArrayList;
        3. Большая задача:
            a. Есть классы Fruit -> Apple, Orange;(больше фруктов не надо)
            b. Класс Box в который можно складывать фрукты, коробки условно сортируются по типу фрукта, поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
            c. Для хранения фруктов внутри коробки можете использовать ArrayList;
            d. Сделать метод getWeight() который высчитывает вес коробки, зная количество фруктов и вес одного фрукта(вес яблока - 1.0f, апельсина - 1.5f, не важно в каких это единицах);
            e. Внутри класса коробка сделать метод compare, который позволяет сравнить текущую коробку с той, которую подадут в compare в качестве параметра, true - если их веса равны, false в противном случае(коробки с яблоками мы можем сравнивать с коробками с апельсинами);
            f. Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую коробку(помним про сортировку фруктов, нельзя яблоки высыпать в коробку с апельсинами), соответственно в текущей коробке фруктов не остается, а в другую перекидываются объекты, которые были в этой коробке;
            g. Не забываем про метод добавления фрукта в коробку.
*/

package ru.geekbrains.lesson1.homework;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static final int SIZE = 20;

    public static Element<String>[] arr = new Element[SIZE];

    public static void main(String[] args) {
        System.out.println("Программа к уроку 1.");

        for (int i = 0; i < SIZE; i++) {
            String str = "s" + String.valueOf(i);
            arr[i] = new Element(str);
        }

        System.out.println("Исходный массив: ");
        arrayOut();

        if (changeArray(2,3)) {
            System.out.println("\nРезультат замены 1: ");
            arrayOut();
        }

        if(changeArray(1,2)) {
            System.out.println("\nРезультат замены 2: ");
            arrayOut();
        }

        ArrayList<String> arrList = arrayToList();
        System.out.println("\nРезультат преобразования массива в ArrayList: ");
        listOut(arrList);
    }

    public static boolean changeArray(int place1, int place2) {
        Element<String> temp = new Element(arr[place1-1].getElement());
        arr[place1-1].setElement(arr[place2-1].getElement());
        arr[place2-1].setElement(temp.getElement());
        return true;
    }

    public static void arrayOut() {
        for(int i = 0; i < arr.length; i++)
            System.out.print(arr[i].getElement() + "  ");
        System.out.println();
    }

    public static ArrayList<String> arrayToList() {
        String[] array = new String[SIZE];
        array = new String[]{"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli",
                "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi",
                "mango", "mushroom", "nut", "olive",
                "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};

        ArrayList arrayList = new ArrayList(Arrays.asList(array));
        return arrayList;
    }

    public static void listOut(ArrayList<String> list) {
        for(int i = 0; i < list.size(); i++)
            System.out.print(list.get(i) + "  ");
        System.out.println();
    }
}
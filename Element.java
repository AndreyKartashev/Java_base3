package ru.geekbrains.lesson1.homework;

public class Element<Type> {

    private Type element;

    public Element(Type obj) {
        this.element = obj;
    }

    public void setElement(Type element) {
        this.element = element;
    }

    public Type getElement() {
        return element;
    }

    public void showType() {
        System.out.println("Тип Type: " + element.getClass().getName());
    }
}

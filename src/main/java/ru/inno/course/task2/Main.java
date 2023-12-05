package ru.inno.course.task2;

public class Main {
    public static void main(String[] args) {
        Able a=Utils.cache(new A());
        a.method();
        a.method();
        a.method();
        a.setValue();
        a.method();
    }
}

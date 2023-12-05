package ru.inno.course.task2;

public class A implements Able{

    @Cache
    @Override
    public void method() {
        System.out.println("original method");
    }

    @Setter
    @Override
    public void setValue() {
        System.out.println("setter method");
    }
}

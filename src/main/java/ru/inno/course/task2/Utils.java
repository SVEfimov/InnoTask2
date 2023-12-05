package ru.inno.course.task2;

import java.lang.reflect.Proxy;

public class Utils {

    public static <T> T cache(T obj) {

        //Получаем загрузчик класса у оригинального объекта
        ClassLoader vasiaClassLoader = obj.getClass().getClassLoader();

        //Получаем все интерфейсы, которые реализует оригинальный объект
        Class[] interfaces = obj.getClass().getInterfaces();

        //Создаем прокси нашего объекта
        return (T) Proxy.newProxyInstance(vasiaClassLoader, interfaces, new ObjectInvocationHandler<>(obj));
    }
}

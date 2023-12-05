package ru.inno.course.task2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;

public class ObjectInvocationHandler<T> implements InvocationHandler {
    private final T resultMethod;
    private final HashMap<Method, Object> cacheList = new HashMap<>();

    public ObjectInvocationHandler(T obj) {

        this.resultMethod = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object result;

        if (method.isAnnotationPresent(Setter.class)) {
            cacheList.clear();
            return method.invoke(resultMethod, args);
        }
        if (method.isAnnotationPresent(Cache.class)) {
            if (cacheList.containsKey(method)) {
                System.out.println("-->Берем значение из Cache");
                return cacheList.get(method);
            }
            else {
                result = method.invoke(resultMethod, args);
                cacheList.put(method, result);
                return result;
            }
        }
        return method.invoke(resultMethod, args);
    }
}

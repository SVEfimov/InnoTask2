package ru.inno.course.task2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;

public class Utils implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }
    public static <T> T cache(T obj) {
        //Прокси объекта
        return (T) Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), new ObjectInvocationHandler<>(obj));
    }

    static private class ObjectInvocationHandler<T> implements InvocationHandler {
        private final T ObjInvocationHandler;
        private final HashMap<Method, Object> cacheList = new HashMap<>();

        public ObjectInvocationHandler(T obj) {
            this.ObjInvocationHandler = obj;
        }
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            Object resultMethod;
            Method runMethod = ObjInvocationHandler.getClass().getMethod(method.getName(), method.getParameterTypes());

            if (runMethod.isAnnotationPresent(Setter.class)) {
                cacheList.clear();
                return method.invoke(ObjInvocationHandler, args);
            }
            if (runMethod.isAnnotationPresent(Cache.class)) {
                if (cacheList.containsKey(method)) {
                    System.out.println("    -->Берем значение из Cache");
                    return cacheList.get(method);
                } else {
                    resultMethod = method.invoke(ObjInvocationHandler, args);
                    cacheList.put(method, resultMethod);
                    return resultMethod;
                }
            }
            return method.invoke(ObjInvocationHandler, args);
        }
    }


}

package com.javarush.khmelov.lesson13.config;


import jakarta.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Winter {

    private Winter() {

    }

    public static Map<Class<?>, Object> beans = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    @SneakyThrows
    public static <T> T find(Class<T> clazz) {
        if (!beans.containsKey(clazz)) {
            Constructor<?> constructor = clazz.getConstructors()[0];
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            Object[] parameters = new Object[parameterTypes.length];
            for (int i = 0; i < parameterTypes.length; i++) {
                parameters[i] = find(parameterTypes[i]);
            }
            Object bean = constructor.newInstance(parameters);
            beans.put(clazz, bean);
        }
        return (T) beans.get(clazz);

    }
}

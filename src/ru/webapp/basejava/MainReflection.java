package ru.webapp.basejava;

import ru.webapp.basejava.model.Resume;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {

    public static void main(String[] args) throws IllegalAccessException,
            NoSuchMethodException,
            InvocationTargetException {
        Resume r = new Resume();
        // TODO : invoke r.toString via reflection
        Method method = r.getClass().getDeclaredMethod("toString");
        System.out.println(method.invoke(r));
    }
}

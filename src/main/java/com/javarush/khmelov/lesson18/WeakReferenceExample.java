package com.javarush.khmelov.lesson18;

import java.lang.ref.WeakReference;

public class WeakReferenceExample {
    public static void main(String[] args) {
        var str = new String("Hello");
        var weakRef = new WeakReference<>(str);
        //слабая ссылка на объект "Hello"
        str = null; // Удаляем сильную ссылку на объект
        // В любой момент система может решить
        // удалить объект из-за отсутствия сильных ссылок
        // можно попытаться получить его из слабой ссылки
        var retrievedStr = weakRef.get();
        if (retrievedStr != null) {
            System.out.println(retrievedStr);
        } else {
            System.out.println("Object collected GC");
        }
    }
}
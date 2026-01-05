package com.javarush.khmelov.lesson18;

import java.lang.ref.SoftReference;

public class SoftReferenceExample {
    public static void main(String[] args) {
        String str = new String("Hello");
        SoftReference<String> softRef = new SoftReference<>(str);
        // мягкая ссылка на объект "Hello"
        str = null; // Удаляем сильную ссылку
        // В любой момент система может решить
        // удалить объект из-за нехватки памяти
        // Мы можем получить объект
        // обратно из мягкой ссылки
        var retrievedStr = softRef.get();
        if (retrievedStr != null) {
            System.out.println(retrievedStr);
        } else {
            System.out.println("Object collected GC");
        }
    }
}
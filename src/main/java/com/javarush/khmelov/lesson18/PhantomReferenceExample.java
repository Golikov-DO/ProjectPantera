package com.javarush.khmelov.lesson18;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.time.LocalTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class PhantomReferenceExample {
    public static void main(String[] args) throws Exception {
        // Создаем очередь для обработки ссылок
        var wait = new ReferenceQueue<String>();
        // Создаем String и PhantomReference
        String s = new String(new char[]{'H', 'i', '!'});
        var ref = new PhantomReference<>(s, wait);
        System.out.println("Live: " + LocalTime.now());
        // Удаляем жесткую ссылку
        s = null;
        // Запускаем GC с паузой 1 сек
        CompletableFuture
                .delayedExecutor(1, TimeUnit.SECONDS)
                .execute(System::gc);
        //Ожидаем на lock-е, пока объект будет собран
        wait.remove();
        System.out.println("Dead: " + LocalTime.now());
    }
}
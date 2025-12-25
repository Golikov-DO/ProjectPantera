package com.javarush.khmelov.lesson16.p06_adapter.adapter;
import java.util.List;

// интерфейс для печати списка текстов
interface PageListPrinter {
    void print(List<String> list);
}

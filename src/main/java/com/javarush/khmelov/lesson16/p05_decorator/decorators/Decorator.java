package com.javarush.khmelov.lesson16.p05_decorator.decorators;

import com.javarush.khmelov.lesson16.p05_decorator.objects.Unit;

abstract class Decorator implements Unit {
    
    private final Unit component;
    
    //ключевой элемент - конструктор с декорируемым элементом
    Decorator (Unit component) {
        this.component = component;
    }
    
    //то что будет делать конкретный декоратор выносится в реализацию
    public abstract void afterDraw();

    //"довесок" к методу
    @Override
    public void draw() {
        component.draw();
        afterDraw();
    }
    
}

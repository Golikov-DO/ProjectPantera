package com.javarush.khmelov.lesson16.p05_decorator.decorators;

import com.javarush.khmelov.lesson16.p05_decorator.objects.Unit;

public class MagicDecorator extends Decorator {
    public MagicDecorator(Unit component) {
        super(component);
    }
    @Override
    public void afterDraw() {
        System.out.println("...added magic");
    }
}

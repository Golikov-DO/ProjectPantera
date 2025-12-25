package com.javarush.khmelov.lesson16.p03_abstract_factory.transport.impl.car;

import com.javarush.khmelov.lesson16.p03_abstract_factory.transport.interfaces.Car;

public class Maz implements Car {
    @Override
    public void drive() {
        System.out.println("Maz drive");
    }

    @Override
    public void stop() {
        System.out.println("Maz stopped");
    }
    
}

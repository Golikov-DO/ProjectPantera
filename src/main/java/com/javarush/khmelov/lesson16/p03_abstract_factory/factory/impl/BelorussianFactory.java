package com.javarush.khmelov.lesson16.p03_abstract_factory.factory.impl;

import com.javarush.khmelov.lesson16.p03_abstract_factory.factory.interfaces.TransportFactory;
import com.javarush.khmelov.lesson16.p03_abstract_factory.transport.impl.aircraft.TU134;
import com.javarush.khmelov.lesson16.p03_abstract_factory.transport.impl.car.Maz;
import com.javarush.khmelov.lesson16.p03_abstract_factory.transport.interfaces.Aircraft;
import com.javarush.khmelov.lesson16.p03_abstract_factory.transport.interfaces.Car;

// российские транспортные средства
public class BelorussianFactory implements TransportFactory{

    @Override
    public Car createCar() {
        return new Maz();
    }

    @Override
    public Aircraft createAircraft() {
        return new TU134();
    }

}

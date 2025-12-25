package com.javarush.khmelov.lesson16.p03_abstract_factory.factory.interfaces;

import com.javarush.khmelov.lesson16.p03_abstract_factory.transport.interfaces.Aircraft;
import com.javarush.khmelov.lesson16.p03_abstract_factory.transport.interfaces.Car;

// фабрика по созданию транспортных средств
public interface TransportFactory { // что фабрика будет производить
    Car createCar();// автомобили
    Aircraft createAircraft(); // самолеты

}

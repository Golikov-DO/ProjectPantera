package com.javarush.khmelov.lesson16.p03_abstract_factory;

import com.javarush.khmelov.lesson16.p03_abstract_factory.factory.impl.BelorussianFactory;
import com.javarush.khmelov.lesson16.p03_abstract_factory.factory.impl.USAFactory;
import com.javarush.khmelov.lesson16.p03_abstract_factory.factory.interfaces.TransportFactory;

public class Start {
    public static void main(String[] args) {
        boolean home = Math.random()>0.5;
        TransportFactory factory;
        if (home){
            factory = new BelorussianFactory();
        }else{
            factory = new USAFactory();
        }
        factory.createAircraft().flight();
        factory.createCar().drive();
    }

}

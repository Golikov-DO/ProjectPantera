package com.javarush.khmelov.lesson16.p07_facade.objects;

import com.javarush.khmelov.lesson16.p07_facade.facade.CarFacade;
import com.javarush.khmelov.lesson16.p07_facade.parts.Clamping;
import com.javarush.khmelov.lesson16.p07_facade.parts.Door;
import com.javarush.khmelov.lesson16.p07_facade.parts.Wheel;

public class Client {

    public static void main(String[] args) {
        // вызов без фасада
        Door door = new Door();
        door.open();

        Clamping clamping = new Clamping();
        clamping.fire();
        
        Wheel wheel = new Wheel();
        wheel.turn();
        

        // вызов с фасадом
        CarFacade carFacade = new CarFacade();
        carFacade.go();
    }
}

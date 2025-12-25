package com.javarush.khmelov.lesson16.p07_facade.facade;

// фасад для работы 

import com.javarush.khmelov.lesson16.p07_facade.parts.Clamping;
import com.javarush.khmelov.lesson16.p07_facade.parts.Door;
import com.javarush.khmelov.lesson16.p07_facade.parts.Wheel;

public class CarFacade {

    private final Door door = new Door();
    private final Clamping clamping = new Clamping();
    private final Wheel wheel = new Wheel();

    public void go(){
        door.open();
        clamping.fire();
        wheel.turn();
    }
    
        
    
}

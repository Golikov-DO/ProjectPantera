package com.javarush.khmelov.lesson16.p05_decorator;

import com.javarush.khmelov.lesson16.p05_decorator.decorators.ColorDecorator;
import com.javarush.khmelov.lesson16.p05_decorator.decorators.MagicDecorator;
import com.javarush.khmelov.lesson16.p05_decorator.objects.Ork;
import com.javarush.khmelov.lesson16.p05_decorator.objects.Rider;
import com.javarush.khmelov.lesson16.p05_decorator.objects.Soldier;
import com.javarush.khmelov.lesson16.p05_decorator.objects.Unit;

public class Start {


    public static void main(String[] args) {

        Unit rider;
        Unit soldier;
        Unit ork;

        boolean showDecoration = Math.random()>0.5;
        if (!showDecoration){
            rider = new Rider();
            soldier = new Soldier();
            ork = new Ork();
        }else{
            rider = new MagicDecorator(new Rider());
            soldier = new MagicDecorator(new Soldier());
            ork = new ColorDecorator(
                    new MagicDecorator(new Ork())
            );
        }

        rider.draw();
        soldier.draw();
        ork.draw();


    }

}

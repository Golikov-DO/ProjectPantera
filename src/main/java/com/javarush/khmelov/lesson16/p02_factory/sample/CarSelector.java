package com.javarush.khmelov.lesson16.p02_factory.sample;

// фабрика по созданию автомобилей
class CarSelector {
    
    // фабричный метод, который создает нужный автомобиль
    Car getCar(RoadType roadType) {
        return switch (roadType) {
            case CITY -> new Mercedes();
            case OFF_ROAD -> new Geep();
            case ROAD -> new Crossover();
        };
    }
}

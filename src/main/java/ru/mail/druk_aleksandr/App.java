package ru.mail.druk_aleksandr;

import ru.mail.druk_aleksandr.servise.CarService;
import ru.mail.druk_aleksandr.servise.ReaderService;
import ru.mail.druk_aleksandr.servise.impl.CarServiceImpl;
import ru.mail.druk_aleksandr.servise.impl.ReaderServiceImpl;

public class App {
    public static void main(String[] args) {
        ReaderService readerService = new ReaderServiceImpl();
        readerService.reader();
        CarService carService = new CarServiceImpl();
        carService.add();
        carService.showsByValueOfEngineCapacity();
        carService.removesByValueOfEngineCapacity();
        carService.showsNumberOfRowsByEngineCapacity();
        carService.updateByValueOfEngineCapacity();
    }
}

package ru.mail.druk_aleksandr.repository;

import ru.mail.druk_aleksandr.repository.model.Car;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface CarRepository {
    void add(Connection connection, Car car) throws SQLException;

    List<Car> showsByValueOfEngineCapacity(Connection connection) throws SQLException;

    void removesByValueOfEngineCapacity(Connection connection) throws SQLException;

    int showsNumberOfRowsByEngineCapacity(Connection connection) throws SQLException;

    List<Car> updateByValueOfEngineCapacity(Connection connection) throws SQLException;
}

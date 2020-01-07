package ru.mail.druk_aleksandr.repository.impl;

import ru.mail.druk_aleksandr.repository.CarRepository;
import ru.mail.druk_aleksandr.repository.model.Car;
import ru.mail.druk_aleksandr.repository.model.CarModelEnum;
import ru.mail.druk_aleksandr.util.RandomUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarRepositoryImpl implements CarRepository {
    @Override
    public void add(Connection connection, Car car) throws SQLException {
        List<Car> cars = generateCarList(10);
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO car(name, carModel, engineCapacity) VALUES (?,?,?)")) {
            for (Car c : cars) {
                preparedStatement.setString(1, c.getName());
                preparedStatement.setString(2, c.getCarModel());
                preparedStatement.setInt(3, c.getEngineCapacity());
                int rows = preparedStatement.executeUpdate();
            }
        }
    }

    @Override
    public List<Car> showsByValueOfEngineCapacity(Connection connection) throws SQLException {
        int valueEngineCapacity = RandomUtil.getElement();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM car WHERE engineCapacity=?")) {
            preparedStatement.setInt(1, valueEngineCapacity);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<Car> cars = new ArrayList<>();
                while (resultSet.next()) {
                    Car car = getCar(resultSet);
                    cars.add(car);
                }
                return cars;
            }
        }
    }

    @Override
    public void removesByValueOfEngineCapacity(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            int rows = statement.executeUpdate("DELETE FROM car WHERE engineCapacity = (SELECT * FROM (SELECT min(engineCapacity) FROM car) AS t1)");
        }
    }

    @Override
    public int showsNumberOfRowsByEngineCapacity(Connection connection) throws SQLException {
        int valueEngineCapacity = RandomUtil.getElement();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM car WHERE engineCapacity=?")) {
            preparedStatement.setInt(1, valueEngineCapacity);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                int count = 0;
                while (resultSet.next()) {
                    count = resultSet.getRow();
                }
                return count;
            }
        }
    }

    @Override
    public List<Car> updateByValueOfEngineCapacity(Connection connection) throws SQLException {
        int valueEngineCapacity = RandomUtil.getElement();
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE car SET name = 'Good capacity' WHERE engineCapacity=?")) {
            preparedStatement.setInt(1, valueEngineCapacity);
            int rows = preparedStatement.executeUpdate();
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery("SELECT * FROM car")) {
                List<Car> cars = new ArrayList<>();
                while (resultSet.next()) {
                    Car car = getCar(resultSet);
                    cars.add(car);
                }
                return cars;
            }
        }
    }

    public static List<Car> generateCarList(int countOfCars) {
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < countOfCars; i++) {
            Car car = Car.newBuilder()
                    .name("Car " + i)
                    .carModel(String.valueOf(generateCarModel()))
                    .engineCapacity(generateEngineCapacity())
                    .build();
            cars.add(car);
        }
        return cars;
    }

    public static int generateEngineCapacity() {
        int minValue = 1;
        int maxValue = 10;
        return RandomUtil.getElement(minValue, maxValue);
    }

    private static CarModelEnum generateCarModel() {
        int index = RandomUtil.getElement(0, CarModelEnum.values().length - 1);
        return CarModelEnum.values()[index];
    }

    private Car getCar(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String carModel = resultSet.getString("carModel");
        int engineCapacity = resultSet.getInt("engineCapacity");
        return Car.newBuilder()
                .name(name)
                .carModel(carModel)
                .engineCapacity(engineCapacity)
                .build();
    }
}

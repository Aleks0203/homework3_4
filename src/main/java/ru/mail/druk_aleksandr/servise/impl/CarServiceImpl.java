package ru.mail.druk_aleksandr.servise.impl;

import ru.mail.druk_aleksandr.repository.CarRepository;
import ru.mail.druk_aleksandr.repository.ConnectionRepository;
import ru.mail.druk_aleksandr.repository.impl.CarRepositoryImpl;
import ru.mail.druk_aleksandr.repository.impl.ConnectionRepositoryImpl;
import ru.mail.druk_aleksandr.repository.model.Car;
import ru.mail.druk_aleksandr.servise.CarService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CarServiceImpl implements CarService {
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private ConnectionRepository connectionRepository = new ConnectionRepositoryImpl();
    private CarRepository carRepository = new CarRepositoryImpl();

    @Override
    public void add() {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            Car car = Car.newBuilder().build();
            try {
                carRepository.add(connection, car);
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void showsByValueOfEngineCapacity() {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                List<Car> cars = carRepository.showsByValueOfEngineCapacity(connection);
                logger.info("Result: " + cars);
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public void removesByValueOfEngineCapacity() {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                carRepository.removesByValueOfEngineCapacity(connection);
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void showsNumberOfRowsByEngineCapacity() {
        try (Connection connection = connectionRepository.getConnection()) {
            int count = 0;
            count = carRepository.showsNumberOfRowsByEngineCapacity(connection);
            logger.info("Result: " + count);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public void updateByValueOfEngineCapacity() {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                List<Car> cars = carRepository.updateByValueOfEngineCapacity(connection);
                logger.info("Result: " + cars);
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }
}

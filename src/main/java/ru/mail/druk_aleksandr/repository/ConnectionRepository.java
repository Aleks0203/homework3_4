package ru.mail.druk_aleksandr.repository;

import java.sql.Connection;

public interface ConnectionRepository {
    Connection getConnection();
}

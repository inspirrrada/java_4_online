package ua.com.alevel.persistance.config;

import java.sql.Connection;
import java.sql.Statement;

public interface JdbcService {
    Connection getConnection();
    Statement getStatement();

}

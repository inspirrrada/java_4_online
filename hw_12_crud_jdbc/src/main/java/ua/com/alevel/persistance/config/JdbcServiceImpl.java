package ua.com.alevel.persistance.config;

import ua.com.alevel.annotations.BeanClass;
import ua.com.alevel.annotations.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@BeanClass
public class JdbcServiceImpl implements JdbcService {

    @Value("jdbc.driver")
    private String driver;

    @Value("jdbc.url")
    private String url;

    @Value("jdbc.username")
    private String username;

    @Value("jdbc.password")
    private String password;

    private Connection connection;
    private Statement statement;

    @Override
    public Connection getConnection() {
        if (connection == null) {
            init();
        }
        return this.connection;
    }

    @Override
    public Statement getStatement() {
        if (statement == null) {
            init();
        }
        return this.statement;
    }

    private void init() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
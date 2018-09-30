package app.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import app.database.DatabaseConnector;

public class BaseDAO {
    protected JdbcTemplate template = DatabaseConnector.getJdbcTemplate();
}

package app.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import app.database.DatabaseConnector;

public class BaseDAO {
    protected static JdbcTemplate template;
    
    public BaseDAO() {
        if(template == null) {
            template = DatabaseConnector.getJdbcTemplate();
        }
    }
}

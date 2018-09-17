package app.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.h2.jdbcx.JdbcDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import app.database.security.DatabaseCredentials;
import app.database.security.DatabaseCredentialsLoader;

/**
 * Creates a database connection using credentials
 * @author artjoms.porss
 *
 */
public class DatabaseConnector {
    private static DatabaseCredentials credentials = new DatabaseCredentials();
    
    private static void loadCredentials() throws IOException {
        if(!credentials.verifyCredentialsPresent()) {
            credentials = DatabaseCredentialsLoader.loadCredentials();
        }
    }
    
    /**
     * Loads database credentials and creates a connection to the database
     * @return Connection
     * @throws SQLException
     * @throws IOException
     */
    public static Connection getConnection() throws SQLException, IOException {
        loadCredentials();
        
        return DriverManager.getConnection(credentials.getDbPath(), credentials.getUser(), credentials.getPassword());
    }
    
    public static JdbcTemplate getJdbcTemplate() {
        try {
            loadCredentials();
        } catch (IOException e) {
            e.printStackTrace();
        }        
        
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL(credentials.getDbPath());
        dataSource.setUser(credentials.getUser());
        dataSource.setPassword(credentials.getPassword());
        
        
        return new JdbcTemplate(dataSource);
    }
}

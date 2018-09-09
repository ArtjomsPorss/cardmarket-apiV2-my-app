package app.database.security;

public class DatabaseCredentialsNotPresentException extends RuntimeException {
    public DatabaseCredentialsNotPresentException(String message) {
        super(message);
    }
}

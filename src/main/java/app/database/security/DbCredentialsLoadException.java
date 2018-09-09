package app.database.security;

public class DbCredentialsLoadException extends RuntimeException {

    private static final long serialVersionUID = 6336794680983583537L;

    public DbCredentialsLoadException(String message) {
        super(message);
    }
}

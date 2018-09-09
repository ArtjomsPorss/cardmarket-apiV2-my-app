package app.security;

public class UserCredentialsLoadException extends RuntimeException {

    private static final long serialVersionUID = 6336794680983583537L;

    public UserCredentialsLoadException(String message) {
        super(message);
    }
}

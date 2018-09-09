package app.security;

public class UserCredentialsNotPresentException extends RuntimeException {

    private static final long serialVersionUID = 5923413761161626639L;

    public UserCredentialsNotPresentException(String message) {
        super(message);
    }
}

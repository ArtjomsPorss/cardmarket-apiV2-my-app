package app.security;

public class CredentialsNotValidException extends RuntimeException {

    private static final long serialVersionUID = 5923413761161626639L;

    public CredentialsNotValidException(String message) {
        super(message);
    }
}

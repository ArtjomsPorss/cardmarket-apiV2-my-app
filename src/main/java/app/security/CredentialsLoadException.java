package app.security;

public class CredentialsLoadException extends RuntimeException {

    private static final long serialVersionUID = 6336794680983583537L;

    public CredentialsLoadException(String message) {
        super(message);
    }
}

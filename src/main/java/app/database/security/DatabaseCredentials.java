package app.database.security;

/**
 * Used to deserialize database credentials from JSON
 * @author artjoms.porss
 *
 */
public class DatabaseCredentials {

    private String dbPath;
    private String user;
    private String password;

    public String getDbPath() {
        return dbPath;
    }

    public void setDbPath(String dbPath) {
        this.dbPath = dbPath;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean verifyCredentialsPresent() {
        if (null == dbPath || dbPath.trim().isEmpty()) {
            return false;
        }
        if (null == user || user.trim().isEmpty()) {
            return false;
        }
        if (null == password || password.trim().isEmpty()) {
            return false;
        }
        return true;
    }

    boolean verifyCredentialsPresentWithException() throws DatabaseCredentialsNotPresentException {
        if (!verifyCredentialsPresent()) {
            throw new DatabaseCredentialsNotPresentException(
                    "Database credential verification problem. One or more credentials not present.");
        }
        return true;
    }
}

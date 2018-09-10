package app.database.security;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.google.gson.Gson;

/**
 * Loads, deserializes database credentials stored somewhere on filesystem.
 * @author artjoms.porss
 *
 */
public class DatabaseCredentialsLoader {
    public static DatabaseCredentials loadCredentials() throws IOException {
        
        /*
        Load credentials from file. Form them in the file as JSON such as:
        {
            "dbPath" : "jdbc:h2:tcp://localhost/~/nameofdb",
            "user" : "kebab",
            "password" : "abrakadabra",
        }
        */
        
        // path to JSON with credentials in it
        Path path = Paths.get("C:\\cardmarketCredentialsDbConnect.txt");

        if (!Files.isReadable(path)) {
            throw new DbCredentialsLoadException(
                    "Failed to load Cardmarket Database Credentials. File specified is NOT readable. Check if it is a folder.");
        }
        // read from path
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);

        // create java object from what is read
        Gson gson = new Gson();
        DatabaseCredentials credentials =  gson.fromJson(String.join("", lines), DatabaseCredentials.class);
        // verify they are present
        credentials.verifyCredentialsPresentWithException();
        
        return credentials;
    }
}

package app.security;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.google.gson.Gson;

public class CredentialsLoader {
    public static Credentials loadCredentials() throws IOException {

        /*
        Load credentials from file. Form them in the file as JSON such as:
        {
            "mkmAppToken" : "bfaD9xOU0SXBhtBP",
            "mkmAppSecret" : "pChvrpp6AEOEwxBIIUBOvWcRG3X9xL4Y",
            "mkmAccessToken" : "lBY1xptUJ7ZJSK01x4fNwzw8kAe5b10Q",
            "mkmAccessTokenSecret" : "hc1wJAOX02pGGJK2uAv1ZOiwS7I9Tpoe"   
        }
        
        */
        // path to JSON
        Path path = Paths.get("C:\\cardmarketCredentials_sandbox.txt");
        if (!Files.isReadable(path)) {
            throw new CredentialsLoadException(
                    "Failed to load Cardmarket Credentials. File specified is readable. Check if it is a folder.");
        }
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);

        Gson gson = new Gson();
        return gson.fromJson(String.join("", lines), Credentials.class);
    }

}

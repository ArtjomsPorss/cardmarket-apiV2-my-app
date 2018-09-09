package app.security;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

public class UserCredentialsLoader {
    
    public static UserCredentials loadCredentials() throws IOException {
        
        

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
            throw new UserCredentialsLoadException(
                    "Failed to load Cardmarket UserCredentials. File specified is NOT readable. Check if it is a folder.");
        }
        // read from path
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);

        // create java object from what is read
        Gson gson = new Gson();
        UserCredentials credentials =  gson.fromJson(String.join("", lines), UserCredentials.class);
        
        credentials.verifyCredentialsPresentWithException();
        
        return credentials;
    }

}

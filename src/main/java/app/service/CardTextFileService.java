package app.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//TODO when there will be UI, this will be triggered and list of cards will be passed from it
public class CardTextFileService {
    public static List<String> loadCardsFromTextFile() {

        /*
        Load cards from file, each card separated by newline e.g.
            Springleaf Drum
            Blightsteel Collossus        
        */
        // path to JSON
        Path path = Paths.get("C:\\cardList.txt");

        if (!Files.isReadable(path)) {
            throw new RuntimeException(
                    "Failed to load textfile with Cards. File specified is NOT readable. Check if it is a folder.");
        }
        // read from path
        List<String> lines = new ArrayList<String>();
        try {
            lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        lines = lines.stream().map(l -> l.trim()).collect(Collectors.toList());
        
        return lines;
    }
}

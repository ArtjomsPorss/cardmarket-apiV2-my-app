package app.service;

import java.util.List;
import java.util.StringJoiner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandLineService {
    
    private static final Logger LOGGER = LogManager.getLogger(CommandLineService.class);
    
    public void printCardsWithExpansions(String cardName, List<String> expansions) {
        StringJoiner sj = new StringJoiner(", ");
        if(expansions.isEmpty()) {
            cardName = cardName + " - NOT FOUND, CHECK IF CARD NAME IS CORRECT!!!";
        } else {
            expansions.stream().forEach(sj::add);
            cardName = cardName + ": " + sj.toString();
        }
        LOGGER.info(cardName);
    }
}

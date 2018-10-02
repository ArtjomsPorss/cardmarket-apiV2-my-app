package app.service;
import app.service.CardTextFileService;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import app.api.ApiCaller;
import app.security.UserCredentials;
import app.security.UserCredentialsLoader;
import entities.Expansion;
import entities.ExpansionWrapper;
import entities.Single;
import entities.SingleWrapper;
import parsers.ApiParserV2_0;
/**
 * Service that wires all other services together into an integrated app.
 * TODO use spring and add front-end framework such as angular that will use REST APIs
 * @author artjoms.porss
 *
 */
public class CardmarketService {
    
    private static final Logger LOGGER = LogManager.getLogger(CardmarketService.class);
    
    private ApiCaller api;
    private ExpansionsService expansionsService = new ExpansionsService();
    private SinglesService singlesService = new SinglesService();
    private CardTextFileService cardTxtFileService = new CardTextFileService();
    private CommandLineService cmd = new CommandLineService();
    
    public CardmarketService()  {
        UserCredentials credentials = UserCredentialsLoader.loadCredentials();
        this.api = new ApiCaller(credentials);
        this.api.setDebug(true);
    }
    
    /**
     * Update database with changes in existing expansions or insert new expansions if not present.
     */
    public void updateExpansions() {
        if (api.request("https://sandbox.cardmarket.com/ws/v2.0/output.json/games/1/expansions")) {
            ExpansionWrapper expansionsWrapper = ApiParserV2_0.processExpansions(api.responseContent());
            expansionsService.insertExpansionsIfNotPresent(expansionsWrapper);
        }        
    }
    
    public void updateAllExpansionSingles() {
        List<Expansion> expansions = expansionsService.getAllExpansions();
        for (Expansion expansion : expansions) {
            updateExpansionSingles(expansion.getIdExpansion());
        }
    }
    
    /**
     * less precise, checks count of singles per expansion in db, and if doesn't match - starts updating from there
     */
    public void updateAllExpansionSinglesByCount() {
        List<Expansion> expansions = expansionsService.getAllExpansions();
        for (Expansion expansion : expansions) {
            updateExpansionSinglesByCount(expansion.getIdExpansion());
        }
    }
    
    /**
     * Update database of singles.
     * Dependency: uses expansions from database.
     * @param expansion
     */
    public void updateExpansionSingles(Integer expansion) {
        if (api.request(String.format("https://sandbox.cardmarket.com/ws/v2.0/output.json/expansions/%d/singles", expansion))) {
            SingleWrapper singlesWrapper = ApiParserV2_0.processExpansionSingles(api.responseContent()); 
            singlesService.insertSinglesIfNotPresent(singlesWrapper);
        }
    }
    
    /**
     * Update database of singles if number of singles per expansion from API and from DB doesn't match.
     * Dependency: uses expansions from database.
     * @param expansion
     */
    public void updateExpansionSinglesByCount(Integer expansion) {
        if (api.request(String.format("https://sandbox.cardmarket.com/ws/v2.0/output.json/expansions/%d/singles", expansion))) {
            SingleWrapper singlesWrapper = ApiParserV2_0.processExpansionSingles(api.responseContent()); 
            singlesService.insertSinglesIfCountNotPresent(singlesWrapper);
        }
    }
    
    public void findSinglesByNames() {
        List<String> cardNames = cardTxtFileService.loadCardsFromTextFile();
        if(null == cardNames || cardNames.isEmpty()) {
            LOGGER.info("The list of cards trying to find in DB is empty!!!");
            return;
        }
        for (String cardName : cardNames) {
            List<Single> singles = singlesService.getSingleByProductName(cardName);
            cmd.printCardsWithExpansions(
                    cardName
                    , singles.stream().map(s -> s.getExpansionName()).collect(Collectors.toList()));;
        }
    }

}

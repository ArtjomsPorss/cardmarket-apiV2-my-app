package app.service;
import app.service.CardTextFileService;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import app.api.ApiCaller;
import app.api.ApiService;
import app.security.UserCredentials;
import app.security.UserCredentialsLoader;
import entities.ArticleWrapper;
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
    
    private ApiService apiService = new ApiService();
    private ExpansionsService expansionsService = new ExpansionsService();
    private SinglesService singlesService = new SinglesService();
    private CardTextFileService cardTxtFileService = new CardTextFileService();
    private CommandLineService cmd = new CommandLineService();
    private ArticlesService articlesService = new ArticlesService(); 
    
    public CardmarketService() {}
    
    /**
     * Update database with changes in existing expansions or insert new expansions if not present.
     */
    public void updateExpansions() {
        ExpansionWrapper expansionsWrapper = apiService.getAllExpansions();
        expansionsService.insertExpansionsIfNotPresent(expansionsWrapper);
    }
    
    @Deprecated
    public void updateAllExpansionSingles() {
        List<Expansion> expansions = expansionsService.getAllExpansions();
        for (Expansion expansion : expansions) {
            updateExpansionSingles(expansion.getIdExpansion());
        }
    }
    
    /**
     * Update database of singles.
     * Dependency: uses expansions from database.
     * @param expansion
     */
    @Deprecated
    public void updateExpansionSingles(Integer expansion) {
        SingleWrapper singlesWrapper = apiService.getSinglesForExpansion(expansion); 
        singlesService.insertSinglesIfNotPresent(singlesWrapper);
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
     * Get singles from API for provided expansion and update/store them database.
     * Dependency: uses expansions from database.
     * @param expansion
     */
    public void updateExpansionSinglesByCount(Integer expansion) {
        SingleWrapper singlesWrapper = apiService.getSinglesForExpansion(expansion); 
        singlesService.insertSinglesIfCountNotPresent(singlesWrapper);
    }
    
    
    /**
     * Get articles for product Id and update/store them in database.
     * @param productId
     */
    public void getArticlesForProduct(Integer productId) {
        ArticleWrapper wrapper = apiService.getArticlesForProduct(productId);
        articlesService.insertArticlesIfNotPresent(wrapper);
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

    public void findArticlesForSingleNames() {
        List<String> cardNamesFromTxtFile = CardTextFileService.loadCardsFromTextFile();
        if(null == cardNamesFromTxtFile || cardNamesFromTxtFile.isEmpty()) {
            LOGGER.info("The list of cards trying to find in DB is empty!!!");
            return;
        }
        for (String cardName : cardNamesFromTxtFile) {
            List<Single> singles = singlesService.getSingleByProductName(cardName);
            //TODO: for each product(expansion) of a card, search for articles and store them in articles table
            for (Single single : singles) {
                if (null == single.getExpansionName() || single.getExpansionName().isEmpty()) {
                    continue;
                } else {
                    getArticlesForProduct(single.getIdProduct());
                }
            }
        }        
    }

}

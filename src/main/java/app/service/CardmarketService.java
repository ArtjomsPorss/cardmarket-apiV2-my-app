package app.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import app.api.ApiService;
import entities.ArticleWrapper;
import entities.Expansion;
import entities.ExpansionWrapper;
import entities.Single;
import entities.SingleWrapper;

/**
 * Service that wires all other services together into an integrated app. TODO
 * use spring and add front-end framework such as angular that will use REST
 * APIs
 * 
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

    public CardmarketService() {
    }

    /**
     * Update database with changes in existing expansions or insert new expansions
     * if not present.
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
     * Update database of singles. Dependency: uses expansions from database.
     * 
     * @param expansion
     */
    @Deprecated
    public void updateExpansionSingles(Integer expansion) {
        SingleWrapper singlesWrapper = apiService.getSinglesForExpansion(expansion);
        singlesService.insertSinglesIfNotPresent(singlesWrapper);
    }

    /**
     * less precise, checks count of singles per expansion in db, and if doesn't
     * match - starts updating from there
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
     * 
     * @param expansion
     */
    public void updateExpansionSinglesByCount(Integer expansion) {
        SingleWrapper singlesWrapper = apiService.getSinglesForExpansion(expansion);
        singlesService.insertSinglesIfCountNotPresent(singlesWrapper);
    }

    /**
     * Get articles for product Id and update/store them in database.
     * 
     * @param productId
     */
    public void getArticlesForProduct(Integer productId) {
        ArticleWrapper wrapper = apiService.getArticlesForProduct(productId);
//        articlesService.insertArticlesIfNotPresent(wrapper);
//        articlesService.insertArticles(wrapper);
        articlesService.insertBatchArticles(wrapper);
    }

    public void findSinglesByNames() {
        List<String> cardNames = cardTxtFileService.loadCardsFromTextFile();
        if (null == cardNames || cardNames.isEmpty()) {
            LOGGER.info("The list of cards trying to find in DB is empty!!!");
            return;
        }
        for (String cardName : cardNames) {
            List<Single> singles = singlesService.getSingleByProductName(cardName);

            cmd.printCardsWithExpansions(cardName,
                    singles.stream().map(s -> s.getExpansionName()).collect(Collectors.toList()));
        }
    }

    public void findArticlesForSingleNames() {
        List<String> listOfCards = CardTextFileService.loadCardsFromTextFile();
        if (null == listOfCards || listOfCards.isEmpty()) {
            LOGGER.info("The list of cards trying to find in DB is empty!!!");
            return;
        }

        Map<String, Integer> cardsAndAmounts = extractCardsAndAmounts(listOfCards);

        // get all Articles for all reprints into DB
        for (String cardName : cardsAndAmounts.keySet()) {
            List<Single> reprints = singlesService.getSingleByProductName(cardName);

            for (Single single : reprints) {
                getArticlesForProduct(single.getIdProduct());
            }
        }

    }

    /**
     * Parses a list of Strings into map of cardName + amount. Skips unknown cards.
     * Does not add card to map if already present.
     * 
     * @param listOfCards
     * @return
     */
    private Map<String, Integer> extractCardsAndAmounts(List<String> listOfCards) {
        if (null == listOfCards || listOfCards.isEmpty()) {
            throw new RuntimeException("NO CARDS IN LIST");
        }
        HashMap<String, Integer> cardAmountMap = new HashMap<>();

        for (String cardAndAmount : listOfCards) {
            Optional<String> cardNameResult = extractCardName(cardAndAmount);

            if (cardNameResult.isPresent()) {
                String cardName = cardNameResult.get();
                Integer cardAmount = extractCardAmount(cardAndAmount);
                cardAmountMap.putIfAbsent(cardName, cardAmount);
            } else if (cardNameResult.get().trim().isEmpty()) {
                continue;
            } else {
                throw new RuntimeException("No cards found in string: " + cardAndAmount);
            }
        }
        return cardAmountMap;
    }

    // TODO move card and amount extraction to a separate service class. Injecting
    // dependent services.
    // TODO possible need of Spring injection arises
    /**
     * Extracts card name from a string. Checks against database of cards for
     * validity.
     * 
     * @param cardAndAmount
     * @return card name if present and correct. Empty optional if not present.
     */
    public Optional<String> extractCardName(String cardAndAmount) {
        if(cardAndAmount.trim().isEmpty()) {
            return Optional.of("");
        }
        
        String number_name_ = "^ *[0-9]++ ([a-zA-Z0-9'\"., ]+) *$";
        Pattern p1 = Pattern.compile(number_name_);
        Matcher m1 = p1.matcher(cardAndAmount);
        if (m1.find()) {
            String cardName = m1.group(1).trim();
            if (!singlesService.getSingleByProductName(cardName).isEmpty()) {
                return Optional.of(cardName);
            }
        }
        String _name_number = "^ *([a-zA-Z0-9'\"., ]+) [0-9]++ *$";
        Pattern p2 = Pattern.compile(_name_number);
        Matcher m2 = p2.matcher(cardAndAmount);
        if (m2.find()) {
            String cardName = m2.group(1).trim();
            if (!singlesService.getSingleByProductName(cardName).isEmpty()) {
                return Optional.of(cardName);
            }
        }
        
        return Optional.empty();
    }

    /**
     * Extracts card amount at beginning or end of string. If no amount found,
     * returns 1
     * 
     * @param cardAndAmount
     * @return amount if found. 1 if not found.
     */
    public Integer extractCardAmount(String cardAndAmount) {
        String _number_name = "^ *([0-9]++) [a-zA-Z0-9'\"., ]+ *$";
        Pattern p1 = Pattern.compile(_number_name);
        Matcher m1 = p1.matcher(cardAndAmount);
        if (m1.find()) {
            return new Integer(m1.group(1).trim());
        }
        String name_number_ = "^ *[a-zA-Z0-9'\"., ]+ ([0-9]++) *$";
        Pattern p2 = Pattern.compile(name_number_);
        Matcher m2 = p2.matcher(cardAndAmount);
        if (m2.find()) {
            return new Integer(m2.group(1).trim());
        }
        return 1; // no amount found, then it is 1 card
    }

}

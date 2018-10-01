package app.service;

import java.util.List;

import app.api.ApiCaller;
import app.security.UserCredentials;
import app.security.UserCredentialsLoader;
import entities.Expansion;
import entities.ExpansionWrapper;
import entities.SingleWrapper;
import parsers.ApiParserV2_0;

public class CardmarketService {
    
    private ApiCaller api;
    private ExpansionsService expansionsService = new ExpansionsService();
    private SinglesService singlesService = new SinglesService();
    
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

}

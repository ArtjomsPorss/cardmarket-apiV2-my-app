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
    
    // TODO update expansions and singles
    // find new expansions --> send to DB
    // for each expansion in DB --> get all singles from API
    // if single doesn't exist - insert it.
    public void updateExpansionsAndSingles() {
        //TODO
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
     * Update database of singles.
     * Dependency: uses expansions from database.
     * @param expansion
     */
    public void updateExpansionSingles(Integer expansion) {
        if (api.request(String.format("https://sandbox.cardmarket.com/ws/v2.0/output.json/expansions/%d/singles", expansion))) {
            SingleWrapper singlesWrapper = ApiParserV2_0.processExpansionSingles(api.responseContent()); 
//            System.out.println(singlesWrapper);
            singlesService.insertSingleIfNotPresent(singlesWrapper);
        }
    }
    
    
    public void updateSingles() {
        
    }
    

}

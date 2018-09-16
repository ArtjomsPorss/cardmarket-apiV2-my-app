package app.service;

import app.dao.ExpansionsDAO;
import entities.Expansion;
import entities.ExpansionWrapper;

public class ExpansionsService {
    
    private ExpansionsDAO dao = new ExpansionsDAO();
    
    /**
     * Inserts new expansions if not present
     * @param expansionsWrapper 
     */
    public void insertExpansionsIfNotPresent(ExpansionWrapper expansionsWrapper) {
        Expansion expansion = new ExpansionsDAO().getExpansion(2110);
        System.out.println(expansion);
        
        // TODO get all expansions from DB. use their id as stream. iterate through expansionsWrapper objects - check which IDs are not present in DB id list. Insert them.
        
    }

}

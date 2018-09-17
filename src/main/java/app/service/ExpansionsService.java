package app.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import app.dao.ExpansionsDAO;
import entities.Expansion;
import entities.ExpansionWrapper;

public class ExpansionsService {
    
    private static final Logger LOGGER = LogManager.getLogger(ExpansionsService.class);
    
    private ExpansionsDAO dao = new ExpansionsDAO();
    
    /**
     * Inserts new expansions if not present in DB. If present but different, updates existing expansion.
     * @param expansionsWrapper 
     */
    public void insertExpansionsIfNotPresent(ExpansionWrapper expansionsWrapper) {
        if(null == expansionsWrapper || null == expansionsWrapper.getExpansion() || expansionsWrapper.getExpansion().isEmpty()) {
            LOGGER.warn("No expansions to process");
            return;
        }
        
        List<Expansion> expansionsFromWeb = expansionsWrapper.getExpansion();
        
        for (Expansion expansionWeb : expansionsFromWeb) {
            // check for expansion with same ID in DB
            Optional<Expansion> optional = dao.getExpansion(expansionWeb.getIdExpansion());
            // if expansion is already present in DB
            if(optional.isPresent()) {
                Expansion expansionDb = optional.get();
                // if they are not equal, update with most recent one
                if(expansionWeb.equals(expansionDb)) {
                    continue;
                } else {
                    updateExpansion(expansionWeb);
                }
            // if expansion is not in DB, insert it
            } else {
                insertExpansion(expansionWeb);
            }
        }
    }

    
    private void updateExpansion(Expansion expansion) {
        dao.updateExpansion(expansion);        
    }

    private void insertExpansion(Expansion expansion) {
        dao.insertExpansion(expansion);        
    }

}

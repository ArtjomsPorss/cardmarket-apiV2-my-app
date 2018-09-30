package app.service;

import java.util.List;
import java.util.Optional;

import app.dao.SinglesDAO;
import entities.Single;
import entities.SingleWrapper;

public class SinglesService {
    
    private SinglesDAO dao = new SinglesDAO();
    
    /**
     * Call DAO to insert single in DB
     * @param single
     */
    public void insertSingle(Single single) {
        dao.insertSingle(single);
    }

    /**
     * 1. Iterates through Singles in SingleWrapper.
     * 2. Calls DAO for a single by product ID from SingleWrapper.
     * 3. If single is returned from DAO, and is different, updates DB.
     * 4. If single is not returned, inserts a new Single into DB. 
     * @param singlesWrapper
     */
    public void insertSingleIfNotPresent(SingleWrapper singlesWrapper) {
        List<Single> singles = singlesWrapper.getSingle();        
        for (Single single : singles) {
            Optional<Single> fromDb = getSingleByProductId(single.getIdProduct());
            if(fromDb.isPresent() && !fromDb.equals(single)) {
                updateSingleSingleById(single);
            } else {
                insertSingle(single);
            }
        }
    }
    
    //TODO get single by ID
    public Optional<Single> getSingleByProductId(Integer productId) {
        return dao.getSingle(productId);
    }
    
    /**
     * Update as single using DAO by product Id.
     * @param single
     */
    public void updateSingleSingleById(Single single) {
        dao.updateSingleById(single);
    }
    
}

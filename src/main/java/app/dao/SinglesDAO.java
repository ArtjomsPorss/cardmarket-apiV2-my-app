package app.dao;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import entities.Single;

public class SinglesDAO extends BaseDAO {
    
    private static final Logger LOGGER = LogManager.getLogger(SinglesDAO.class);
    
    public void insertSingle(Single single) {
        LOGGER.debug(String.format("Inserting an single into DB [%s]", single));
        
        String query = "insert into TSCM_SINGLES (SGL_ID_PRODUCT, SGL_ID_METAPRODUCT, SGL_REPRINT_AMT, SGL_EN_NAME, SGL_LOC_NAME, SGL_ID_GAME, SGL_EXP_NAME, SGL_RARITY, SGL_IMAGE) VALUES(?,?,?,?,?,?,?,?,?);";
        template.update(query,
                single.getIdProduct(),
                single.getIdMetaproduct(),
                single.getCountReprints(),
                single.getEnName(),
                single.getLocName(),
                single.getIdGame(),
                single.getExpansionName(),
                single.getRarity(),
                single.getImage());        
    }
    
    /**
     * Getting Single from DB by Product Id
     * @param productId
     * @return
     */
    public Optional<Single> getSingle(Integer productId) {
//        LOGGER.debug(String.format("Getting a single from DB with id[%d]", productId));
        
        String sql = "select * from TSCM_SINGLES where SGL_ID_PRODUCT = ?;";
        List<Single> singles = template.query(sql, new Object[] {productId}, new SingleRowMapper());
        return null != singles && !singles.isEmpty() ? Optional.ofNullable(singles.get(0)) : Optional.ofNullable(null);
    }
    
    public void updateSingleById(Single single) {
        
    }
}

package app.dao;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import entities.Expansion;
import entities.ExpansionWrapper;

public class ExpansionsDAO extends BaseDAO {
    
    private static final Logger LOGGER = LogManager.getLogger(ExpansionsDAO.class);
    /**
     * Returns an expansion with specified ID from DB
     * @param idExpansion
     * @return
     */
    public Optional<Expansion> getExpansion(Integer idExpansion) {
        LOGGER.debug(String.format("Getting an expansion from DB with id[%d]", idExpansion));
        
        String sql = "select * from TSCM_EXPANSIONS where EXP_ID_EXPANSION = ?;";
        List<Expansion> expansions = template.query(sql, new Object[] {idExpansion}, new ExpansionsRowMapper());
        return null != expansions && !expansions.isEmpty() ? Optional.ofNullable(expansions.get(0)) : Optional.ofNullable(null);
    }
    
    /**
     * Returns all expansions present in DB
     * @return
     */
    public List<Expansion> getAllExpansions() {
        LOGGER.debug("Getting all expansions from DB");
        String sql = "select * from TSCM_EXPANSIONS;";
        List<Expansion> expansions = template.query(sql, new ExpansionsRowMapper());
        return expansions;
    }
    
    
    
    /**
     * Inserts expansion in to DB
     * @param expansion
     */
    public void insertExpansion(Expansion expansion) {
        LOGGER.debug(String.format("Inserting an expansion into DB [%s]", expansion));
        
        String query = "insert into TSCM_EXPANSIONS (EXP_ID_EXPANSION, EXP_EN_NAME, EXP_ABBREVIATION, EXP_RELEASE_DATE, EXP_IS_RELEASED, EXP_ID_GAME) VALUES(?,?,?,?,?,?);";
        template.update(
                query
                , expansion.getIdExpansion()
                , expansion.getEnName()
                , expansion.getAbbreviation()
                , expansion.getReleaseDateAsOffsetDateTime()
                , expansion.isIsReleased()
                , expansion.getIdGame());
    }

    public void updateExpansion(Expansion expansion) {
        LOGGER.debug(String.format("Updating an expansion in DB to [%s]", expansion));

        String query = "update TSCM_EXPANSIONS SET EXP_EN_NAME = ?, EXP_ABBREVIATION = ?, EXP_RELEASE_DATE = ?, EXP_IS_RELEASED = ?, EXP_ID_GAME = ? WHERE EXP_ID_EXPANSION = ?;";
        template.update(
                query
                , expansion.getEnName()
                , expansion.getAbbreviation()
                , expansion.getReleaseDateAsOffsetDateTime()
                , expansion.isIsReleased()
                , expansion.getIdGame()
                , expansion.getIdExpansion());
    }
}

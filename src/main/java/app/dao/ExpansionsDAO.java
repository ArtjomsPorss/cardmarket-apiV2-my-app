package app.dao;

import java.io.IOException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import app.database.DatabaseConnector;
import entities.Expansion;
import entities.ExpansionWrapper;

public class ExpansionsDAO {
    JdbcTemplate template = DatabaseConnector.getJdbcTemplate();
    
    public Expansion getExpansion(Integer idExpansion) {
        String sql = "select * from TSCM_EXPANSIONS where EXP_ID_EXPANSION = ?;";
        List<Expansion> expansions = template.query(sql, new Object[] {idExpansion}, new ExpansionRowMapper());
        return null != expansions && !expansions.isEmpty() ? expansions.get(0) : new Expansion();
    }
    
    public List<Expansion> getAllExpansions() {
        String sql = "select * from TSCM_EXPANSIONS;";
        List<Expansion> expansions = template.query(sql, new ExpansionRowMapper());
        return expansions;
    }
    
    
    public void insertExpansions(ExpansionWrapper wrapper) throws IOException {
        List<Expansion> expansions = wrapper.getExpansion();
        
        String query = "insert into TSCM_EXPANSIONS (EXP_ID_EXPANSION, EXP_EN_NAME, EXP_ABBREVIATION, EXP_RELEASE_DATE, EXP_IS_RELEASED, EXP_ID_GAME) VALUES(?,?,?,?,?,?);";
        
        for (Expansion expansion : expansions) {
            template.update(
                    query
                    , expansion.getIdExpansion()
                    , expansion.getEnName()
                    , expansion.getAbbreviation()
                    , expansion.getReleaseDateAsOffsetDateTime()
                    , expansion.isIsReleased()
                    , expansion.getIdGame());
        }
    }
}

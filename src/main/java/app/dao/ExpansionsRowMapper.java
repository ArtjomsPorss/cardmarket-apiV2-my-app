package app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;

import org.springframework.jdbc.core.RowMapper;

import entities.Expansion;

public class ExpansionsRowMapper implements RowMapper<Expansion> {

    public Expansion mapRow(ResultSet rs, int rowNum) throws SQLException {
        Expansion temp = new Expansion(); 
        temp.setIdExpansion(rs.getInt("EXP_ID_EXPANSION"));
        temp.setEnName(rs.getString("EXP_EN_NAME"));
        temp.setAbbreviation(rs.getString("EXP_ABBREVIATION"));
        OffsetDateTime timestamp = rs.getObject("EXP_RELEASE_DATE", OffsetDateTime.class);
        temp.setReleaseDateFromOffsetDateTime(timestamp);
        // original 2018-08-10T00:00:00+0200
        // from db  2018-08-10T00:00+02:00
//        java.sql.Timestamp timestamp = rs.getTimestamp("EXP_RELEASE_DATE");
//        TimestampWithTimeZone timestamp = (TimestampWithTimeZone) rs.getObject("EXP_RELEASE_DATE");
        temp.setIsReleased(rs.getBoolean("EXP_IS_RELEASED"));
        temp.setIdGame(rs.getString("EXP_ID_GAME"));
                
        return temp;
    }
}

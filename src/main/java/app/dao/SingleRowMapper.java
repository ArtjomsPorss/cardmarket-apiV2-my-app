package app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import entities.Single;

public class SingleRowMapper implements RowMapper<Single> {

    @Override
    public Single mapRow(ResultSet rs, int rowNum) throws SQLException {
        Single temp = new Single();
        temp.setIdProduct(rs.getInt("SGL_ID_PRODUCT"));
        temp.setIdMetaproduct(rs.getInt("SGL_ID_METAPRODUCT"));
        temp.setCountReprints(rs.getInt("SGL_REPRINT_AMT"));
        temp.setEnName(rs.getString("SGL_EN_NAME"));
        temp.setLocName(rs.getString("SGL_LOC_NAME"));
        temp.setIdGame(rs.getString("SGL_ID_GAME"));
        temp.setExpansionName(rs.getString("SGL_EXP_NAME"));
        temp.setRarity(rs.getString("SGL_RARITY"));
        temp.setImage(rs.getString("SGL_IMAGE"));
        return temp;
    }

}

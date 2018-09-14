package app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import entities.Article;
import entities.Language;
import entities.User;

public class ArticlesRowMapper implements RowMapper {

    @Override
    public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
        Article temp = new Article(
                rs.getInt("ART_ID_ARTICLE")
              , rs.getInt("ART_ID_PRODUCT")
              , new Language(rs.getInt("ART_ID_LANGUAGE"))
              , rs.getString("ART_COMMENTS")
              , rs.getBigDecimal("ART_PRICE")
              , rs.getInt("ART_COUNT")
              , rs.getBoolean("ART_IN_SHOPPING_CART")
              , new User(rs.getInt("ART_ID_USER"))
              , rs.getString("ART_CONDITION")
              , rs.getBoolean("ART_IS_FOIL")
              , rs.getBoolean("ART_IS_SIGNED")
              , rs.getBoolean("ART_IS_PLAYSET")
              , rs.getBoolean("ART_IS_ALTERED")
              , null);
          return temp;
    }
}

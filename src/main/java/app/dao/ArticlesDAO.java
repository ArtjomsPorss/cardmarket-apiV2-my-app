package app.dao;

import java.io.IOException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import app.database.DatabaseConnector;
import entities.Article;
import entities.ArticleWrapper;
import entities.Language;
import entities.User;

public class ArticlesDAO {
    
    JdbcTemplate template = DatabaseConnector.getJdbcTemplate();
    
    public Article getArticle(Integer idArticle) {
        String sql = "select * from tdcm_articles where art_id_article = ?;";
        
        Article article = template.query(sql, new Object[] {idArticle}, r -> {
                Article temp = new Article(
                      r.getInt("ART_ID_ARTICLE")
                    , r.getInt("ART_ID_PRODUCT")
                    , new Language(r.getInt("ART_ID_LANGUAGE"))
                    , r.getString("ART_COMMENTS")
                    , r.getBigDecimal("ART_PRICE")
                    , r.getInt("ART_COUNT")
                    , r.getBoolean("ART_IN_SHOPPING_CART")
                    , new User(r.getInt("ART_ID_USER"))
                    , r.getString("ART_CONDITION")
                    , r.getBoolean("ART_IS_FOIL")
                    , r.getBoolean("ART_IS_SIGNED")
                    , r.getBoolean("ART_IS_PLAYSET")
                    , r.getBoolean("ART_IS_ALTERED")
                    , null);
                return temp;
            }
        );
        
        return article;
    }
    
    
    public void insertArticles(ArticleWrapper wrapper) throws IOException {
        List<Article> articles = wrapper.getArticle();
        
        String query = "insert into tdcm_articles(ART_ID_ARTICLE, ART_ID_PRODUCT, ART_ID_LANGUAGE, ART_COMMENTS, ART_PRICE, ART_COUNT, ART_IN_SHOPPING_CART, ART_ID_USER, ART_CONDITION, ART_IS_FOIL, ART_IS_SIGNED, ART_IS_PLAYSET, ART_IS_ALTERED) values(?,?,?,?,?,?,?,?,?,?,?,?,?);";
        
        for (Article article : articles) {
            template.update(query, article.getIdArticle(), article.getIdProduct(), article.getLanguage().getIdLanguage(), article.getComments(), article.getPrice(), article.getCount(), article.getInShoppingCart(), article.getSeller().getIdUser(), article.getCondition(), article.getIsFoil(), article.getIsSigned(), article.getIsPlayset(), article.getIsAltered());
        }
    }
}

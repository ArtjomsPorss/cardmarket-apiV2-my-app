package app.dao;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import entities.Article;
import entities.ArticleWrapper;

public class ArticlesDAO extends BaseDAO {
    
    public Article getArticle(Integer idArticle) {
        String sql = "select * from tdcm_articles where art_id_article = ?;";
        
        List<Article> articles = template.query(sql, new Object[] {idArticle}, new ArticlesRowMapper());
        
        return null != articles && !articles.isEmpty() ? articles.get(0) : new Article();
    }
    
    
    public void insertArticles(ArticleWrapper wrapper) throws IOException {
        wrapper.getArticle().forEach(a -> insertArticle(a));
    }


    public void insertArticle(Article article) {
        String query = "insert into tdcm_articles(ART_ID_ARTICLE, ART_ID_PRODUCT, ART_ID_LANGUAGE, ART_COMMENTS, ART_PRICE, ART_COUNT, ART_IN_SHOPPING_CART, ART_ID_USER, ART_CONDITION, ART_IS_FOIL, ART_IS_SIGNED, ART_IS_PLAYSET, ART_IS_ALTERED) values(?,?,?,?,?,?,?,?,?,?,?,?,?);";
        template.update(query, article.getIdArticle(), article.getIdProduct(), article.getLanguage().getIdLanguage(), article.getComments(), article.getPrice(), article.getCount(), article.getInShoppingCart(), article.getSeller().getIdUser(), article.getCondition(), article.getIsFoil(), article.getIsSigned(), article.getIsPlayset(), article.getIsAltered());
    }
    

    public void insertBatchArticles(final List<Article> articles) {
        String sql = "insert into tdcm_articles(ART_ID_ARTICLE, ART_ID_PRODUCT, ART_ID_LANGUAGE, ART_COMMENTS, ART_PRICE, ART_COUNT, ART_IN_SHOPPING_CART, ART_ID_USER, ART_CONDITION, ART_IS_FOIL, ART_IS_SIGNED, ART_IS_PLAYSET, ART_IS_ALTERED) values(?,?,?,?,?,?,?,?,?,?,?,?,?);";
                        
              template.batchUpdate(sql, new BatchPreparedStatementSetter() {
                        
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Article article = articles.get(i);
                    ps.setInt(1, article.getIdArticle());
                    ps.setInt(2, article.getIdProduct());
                    ps.setInt(3, article.getLanguage().getIdLanguage());
                    ps.setString(4, article.getComments());
                    ps.setBigDecimal(5, article.getPrice());
                    ps.setInt(6, article.getCount());
                    ps.setBoolean(7, article.getInShoppingCart());
                    ps.setInt(8, article.getSeller().getIdUser());
                    ps.setString(9, article.getCondition());
                    ps.setBoolean(10, article.getIsFoil());
                    ps.setBoolean(11, article.getIsSigned());
                    ps.setBoolean(12, article.getIsPlayset());
                    ps.setBoolean(13, article.getIsAltered());
                }
                        
                @Override
                public int getBatchSize() {
                    return articles.size();
                }
              });
        
    }
}

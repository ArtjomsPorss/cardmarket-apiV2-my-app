package app.service;

import app.dao.ArticlesDAO;
import entities.Article;
import entities.ArticleWrapper;

public class ArticlesService {
    
    private ArticlesDAO dao = new ArticlesDAO();
    
    public void insertArticleIfNotPresent(Article article) {
        if(null == article) {
            return;
        }  
        // check article is not in DB
        Article fromDb = getArticle(article.getIdArticle());
        if(null != fromDb && fromDb.getIdArticle() == article.getIdArticle()) {
            return;
        } else {
            insertArticle(article);
        }
    }
    
    private void insertArticle(Article article) {
        dao.insertArticle(article);
    }

    public Article getArticle(Integer idArticle) {
        return dao.getArticle(idArticle);
    }

    public void insertArticles(ArticleWrapper wrapper) {
        wrapper.getArticle().forEach(a -> insertArticleIfNotPresent(a));
    }
}

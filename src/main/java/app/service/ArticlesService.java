package app.service;

import app.dao.ArticlesDAO;
import entities.Article;

public class ArticlesService {
    
    private ArticlesDAO dao = new ArticlesDAO();
    
    public void persistArticle(Article article) {
        if(null == article) {
            throw new NullPointerException("Article is null!");
        }
        
        //TODO persist only one article
    }
    
    public Article getArticle(Integer idArticle) {
        return dao.getArticle(idArticle);
    }
}

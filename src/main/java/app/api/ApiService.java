package app.api;

import app.security.UserCredentials;
import app.security.UserCredentialsLoader;
import entities.ArticleWrapper;
import entities.ExpansionWrapper;
import entities.SingleWrapper;
import parsers.ApiParserV2_0;

/**
 * Service class that organises calls to cardmarket Rest API caller class to get json
 * It uses response json parser to return pojo's  
 * @author Artjom Porsh
 *
 */
public class ApiService {

    //TODO: runtime exceptions thrown in this class are candidates for an Aspect
    
    private ApiCaller api;
    
    //TODO: apply switch from sandbox to real urls, don't forget about credentials
    public static boolean isSandbox = true;    
    private static String SANDBOX_URL = "https://sandbox.cardmarket.com/ws/v2.0/output.json/";
    private static String REAL_URL = "https://api.cardmarket.com/ws/v1.1/";

    public ApiService() {
        UserCredentials credentials = UserCredentialsLoader.loadCredentials();
        this.api = new ApiCaller(credentials);
        this.api.setDebug(true);
    }
    
    /**
     * Get all expansions from the database.
     */
    public ExpansionWrapper getAllExpansions() {
        if (api.request("https://sandbox.cardmarket.com/ws/v2.0/output.json/games/1/expansions")) {
            return ApiParserV2_0.processExpansions(api.responseContent());
        } else {
            throw new RuntimeException("updateExpansions failed!");
        }
    }    
    
    /**
     * Get all singles for expansion id
     * Dependency: uses expansions from database.
     * @param expansion
     */
    public SingleWrapper getSinglesForExpansion(Integer expansion) {
        if (api.request(String.format("https://sandbox.cardmarket.com/ws/v2.0/output.json/expansions/%d/singles", expansion))) {
            return ApiParserV2_0.processExpansionSingles(api.responseContent()); 
        } else {
            throw new RuntimeException("updateExpansionSinglesByCount failed!");
        }
    }

    /**
     * Get articles for product Id (if a Single) from cardmarket Rest API
     * 
     * @param productId
     */
    public ArticleWrapper getArticlesForProduct(Integer productId) {
        if (api.request("https://sandbox.cardmarket.com/ws/v1.1/output.json/articles/" + productId.toString())) {
            return ApiParserV2_0.processFindArticles(api.responseContent());
        } else {
            throw new RuntimeException("getArticlesForProduct: failed!");
        }
    }
    
}

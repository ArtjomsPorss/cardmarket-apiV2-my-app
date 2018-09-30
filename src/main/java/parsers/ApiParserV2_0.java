package parsers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import app.security.UserCredentialsLoader;
import entities.AccountWrapper;
import entities.ArticleWrapper;
import entities.ExpansionWrapper;
import entities.GameWrapper;
import entities.MetaproductWrapper;
import entities.MetaproductsWrapper;
import entities.ProductWrapper;
import entities.ProductsWrapper;
import entities.SingleWrapper;
import entities.UserWrapper;
import entities.UsersWrapper;

public class ApiParserV2_0 {
    
    private static final Logger LOGGER = LogManager.getLogger(ApiParserV2_0.class);
    
    /**
     * GET - Returns the account details of the authenticated user Authentication -
     * Required Response Format - XML or JSON Response Object - Account API Version
     * - 2.0 Resource URI - https://api.cardmarket.com/ws/v2.0/account
     */
    public static AccountWrapper processAccount(String responseContent) {
        Gson gson = new Gson();
        AccountWrapper accountWrapper = gson.fromJson(responseContent, AccountWrapper.class);
        return accountWrapper;
    }
    
    
    
    /**
     * GET - Returns all games supported by MKM and you can sell and buy products
     * for. Authentication - Only required for App Response Format - XML or JSON
     * Response Object - Game API Version - 2.0 Resource URI -
     * https://api.cardmarket.com/ws/v2.0/games
     */
    public static GameWrapper processGames(String responseContent) {
        Gson gson = new Gson();
        GameWrapper games = gson.fromJson(responseContent, GameWrapper.class);
        return games;
    }
    
    
    
    /**
     * Allowed HTTP Methods: GET - Returns all expansions with single cards for the
     * specified game.
     * 
     * Resource Information: Authentication - Only required for App Response Format
     * - XML or JSON Response Object - Expansion API Version - 2.0 Resource URI -
     * https://api.cardmarket.com/ws/v2.0/games/:idGame/expansions
     * 
     * Parameters: * idGame Game ID the single cards are requested for, as retrieved
     * from the Games request Type: integer Mandatory
     * 
     * Example Request GET https://api.cardmarket.com/ws/v2.0/games/1/expansions
     */
    public static ExpansionWrapper processExpansions(String responseContent) throws JsonParseException {
        Gson gson = new Gson();
        ExpansionWrapper expansionsWrapper = gson.fromJson(responseContent, ExpansionWrapper.class);
        
        if(null == expansionsWrapper || null == expansionsWrapper.getExpansion() || expansionsWrapper.getExpansion().isEmpty()) {
            LOGGER.warn("No expansions received from the web");
        }
        
        return expansionsWrapper;
       
    }
    
    
    
    /**
     * Expansion Singles: Allowed HTTP Methods: GET - Returns all single cards for
     * the specified expansion.
     * 
     * Resource Information: Authentication - Only required for App Response Format
     * - XML or JSON Response Object - Expansion, Product (without details) API
     * Version - 2.0 Resource URI -
     * https://api.cardmarket.com/ws/v2.0/expansions/:idExpansion/singles
     * 
     * Parameters: idExpansion Expansion ID the single cards are requested for Type:
     * integer Mandatory
     * 
     * Example Request: GET
     * https://api.cardmarket.com/ws/v2.0/expansions/1469/singles
     */
    public static SingleWrapper processExpansionSingles(String responseContent) throws JsonParseException {
        Gson gson = new Gson();
        SingleWrapper singles = gson.fromJson(responseContent, SingleWrapper.class);
        return singles;
    }
    
    
    
    /**
     * Find products by specfied name or part of it. Search by part of name requires more parameters
     * Find Products:
     * https://www.mkmapi.eu/ws/documentation/API_2.0:Find_Products
     *  Allowed HTTP Methods
     *  GET - Searches for products by a given search string
     * Resource Information:
     *  Authentication - Only required for App
     *  Response Format - XML or JSON
     *  Response Object - Product (without details!)
     *  API Version - 2.0
     *  Resource URI - https://api.cardmarket.com/ws/v2.0/products/find
     * Parameters:
     *  search (query parameter)
     *      search string
     *      Type: string
     *      Mandatory
     *  exact (query parameter)
     *      indicates if only products should be returned where the name exactly matches the search string
     *      Type: boolean (true|false)
     *      Optional
     *  idGame (query parameter)
     *      Game ID
     *      Type: int
     *      Optional (if not provided, 1 for Magic is default
     *  idLanguage (query parameter)
     *      Language ID the search string is provided in
     *      Type: int
     *      Optional (if not provided, 1 for English is default
     *  start and maxResults (query parameter)
     *      If specified, only maxResults entities are returned starting from start
     *      Type: integer and integer
     *      Optional (if provided, both must be provided)
     *      See also: Partial Content Documentation
     * Example Request:
     *  1. Search for Magic products with "Springleaf" in their English name (will return "Springleaf Drum" from Born of the Gods and Lorwyn):
     *      GET https://api.cardmarket.com/ws/v2.0/products/find?search=Springleaf&idGame=1&idLanguage=1
     *  2. Search for Magic products with the exact English name "Springleaf Drum" (will return "Springleaf Drum" from Born of the Gods and Lorwyn):
     *      GET https://api.cardmarket.com/ws/v2.0/products/find?search=Springleaf%20Drum&exact=true&idGame=1&idLanguage=1
     */
        public static ProductsWrapper processFindProducts(String responseContent) {
        Gson gson = new Gson();
        ProductsWrapper product = gson.fromJson(responseContent, ProductsWrapper.class);
        return product;
    }
    
        
    
    /**
     * Product:
     * Allowed HTTP Methods:
     *  GET - Returns a product specified by its ID
     * 
     * Resource Information:
     *  Authentication - Only required for App
     *  Response Format - XML or JSON
     *  Response Object - Product (detailed)
     *  API Version - 2.0
     *  Resource URI - https://api.cardmarket.com/ws/v2.0/products/:idProduct
     * 
     * Parameters:
     *  idProduct:
     *  Product ID
     *  Type: integer
     *  Mandatory
     *  
     * Example Request:
     * GET https://api.cardmarket.com/ws/v2.0/products/265535
     */
    public static ProductWrapper processProduct(String responseContent) {
        Gson gson = new Gson();
        ProductWrapper product = gson.fromJson(responseContent, ProductWrapper.class);
        return product;
    }
    
    
    
    /**
     * Find all Articles for specified product id.
     * 
     * ARTICLES:
     * 	Allowed HTTP Methods:
     * 		GET - Returns all available articles for a specified product. You can specify parameters for start and maximum results returned. If the response would have more than 1.000 entities a Temporary Redirect is returned. You can specify several filter parameters.
     * 	Resource Information:
     * 		Authentication - Only required for App
     * 		Response Format - XML or JSON
     * 		Response Object - Article
     * 		API Version - 2.0
     * 		Resource URI - https://api.cardmarket.com/ws/v2.0/articles/:idProduct
     * 	Parameters:
     * 		idProduct:
     * 			Product ID
     * 			Type: integer
     * 			Mandatory
     * 		start and maxResults (query parameter):
     * 			If specified, only maxResults entities are returned starting from start
     * 			Type: integer and integer
     * 			Optional (if provided, both must be provided)
     * 			See also: Partial Content Documentation
     * 		userType (query parameter):
     * 			only articles from sellers with the specified user type are returned (private for private sellers only; commercial for all commercial sellers, including powersellers; powerseller for powersellers only)
     * 			Type: string (with values as stated above)
     * 			Optional
     * 		minUserScore (query parameter):
     * 			only articles from sellers with the sepcified user score or better are returned (1 for outstanding > 2 for very good > 3 good > 4 for average > 5 for bad)
     * 			Type: int (with values as stated above)
     * 			Optional
     * 		idLanguage (query parameter):
     * 			only articles are returned that match the give language (1 for English; 2 for French; 3 for German; 4 for Spanish; 5 for Italian; 6 for Simplified Chinese; 7 for Japanese; 8 for Portuguese; 9 for Russian; 10 for Korean; 11 for Traditional Chinese)
     * 			Type: int (with values as stated above)
     * 			Optional
     * 		minCondition (query parameter):
     * 			only articles with the specified condition or better are returned (MT for Mint > NM for Near Mint > EX for Exellent > GD for Good > LP for Light Played > PL for Played > PO for Poor)
     * 			Type: string (with values as stated above)
     * 			Optional
     * 		isFoil (query parameter):
     * 			only articles that are flagged as foil are returned
     * 			Type: bool (true|false)
     * 			Optional
     * 		isSigned (query parameter):
     * 			only articles that are flagged as signed are returned
     * 			Type: bool (true|false)
     * 			Optional
     * 		isAltered (query parameter):
     * 			only articles that are flagged as altered are returned
     * 			Type: bool (true|false)
     * 			Optional
     * 		minAvailable (query parameter):
     * 			only articles with a minimum amount as specified are returned
     * 			Type: int
     * 			Optional
     * 	Example Request:
     * 		1. Get all articles for the product with idProduct 266361:     
     * 		GET https://api.cardmarket.com/ws/v2.0/articles/266361      
     * 		2. Get articles for the product with idProduct 266361 that      
     * 		are from private sellers (userType=private,
     * 		are in English (idLanguage=1),
     * 		and are in minimum near mint condition (minCondition=NM),
     * 		return only 10 entities starting from 0:
     * 		GET https://api.cardmarket.com/ws/v2.0/articles/266361?userType=private&idLanguage=1&minCondition=NM&start=0&maxResults=10
     * 
     * @param responseContent
     * @return
     */
    public static ArticleWrapper processFindArticles(String responseContent) {
        Gson gson = new Gson();
        ArticleWrapper article = gson.fromJson(responseContent, ArticleWrapper.class);
        return article;
    }
    
    
    
    /**
     *  METAPRODUCTS:
     *  	Allowed HTTP Methods:
     *  		GET - Returns the metaproduct specified by its ID
     *  	Resource Information:
     *  		Authentication - Only required for App
     *  		Response Format - XML or JSON
     *  		Response Object - Metaproduct
     *  		API Version - 2.0
     *  		Resource URI - https://api.cardmarket.com/ws/v2.0/metaproducts/:idMetaproduct
     *  	Parameters:
     *  		idMetaproduct
     *  			Metaproduct ID
     *  			Type: integer
     *  			Mandatory
     * 
     *  	Example Request:
     *  		Return the metaproduct with the ID 9074:
     *  		GET https://api.cardmarket.com/ws/v2.0/metaproducts/9074
     * 
     * @param responseContent
     * @return
     */
    public static MetaproductWrapper processMetaproducts(String responseContent) {
        Gson gson = new Gson();
        MetaproductWrapper article = gson.fromJson(responseContent, MetaproductWrapper.class);
        return article;
    }
    
    
    
    /**
     * FIND METAPRODUCTS
     * 	Allowed HTTP Methods:
     * 		GET - Returns a metaproduct specified by an name to search for, a flag indicating if the metaproduct's name must exactly match the search string, the language ID the search term is given in and the game ID the metacard is searched for.
     * 	Resource Information:
     * 		Authentication - Only required for App
     * 		Response Format - XML or JSON
     * 		Response Object - Metaproduct
     * 		API Version - 2.0
     * 		Resource URI - https://api.cardmarket.com/ws/v2.0/metaproducts/find?
     * 	Parameters:
     * 		search (query parameter):
     * 			search string
     * 			Type: string
     * 			Mandatory
     * 		exact (query parameter):
     * 			Indicates if the given search string must exactly match the metaproduct's name
     * 			Type: boolean (true|false)
     * 			Optional (if not provided, true is default
     * 		idGame (query parameter):
     * 			Game ID
     * 			Type: int
     * 			Optional (if not provided, 1 for Magic is default
     * 		idLanguage (query parameter):
     * 			Language ID the search string is provided in
     * 			Type: int
     * 			Optional (if not provided, 1 for English is default
     * 	Example Request:
     * 	    1. Search for a metaproduct with the exact name "Tarmogoyf". As no game and language IDs are provided, the search term is provided in English and Magic is the game the metacard is searched for:
     * 	        GET https://api.cardmarket.com/ws/v2.0/metaproducts/find?search=Tarmogoyf
     * 	    2. Search for a metaproduct with "Schwarzes" in it's name, given in German and searched for Yugioh:
     * 	        GET https://api.cardmarket.com/ws/v2.0/metaproducts/find?search=Schwarzes%20Loch&exact=false&idGame=3&idLanguage=3
     * 
     * @param responseContent
     * @return
     */
    public static MetaproductsWrapper processFindMetaproducts(String responseContent) {
        Gson gson = new Gson();
        MetaproductsWrapper metaproduct = gson.fromJson(responseContent, MetaproductsWrapper.class);
        return metaproduct;
    }
    
    
    
    /**
     * Returns a specific user
     * 
     * USERS:
     * 	Allowed HTTP Methods:
     * 		GET - Returns the user specified either by its ID, or its exact name.
     * 	Resource Information:
     * 		Authentication - Only required for App
     * 		Response Format - XML or JSON
     * 		Response Object - User
     * 		API Version - 2.0
     * 		Resource URI - https://api.cardmarket.com/ws/v2.0/users/:idUser
     * 	Parameters:
     * 		idUser:
     * 			User ID or Username
     * 			Type: integer (ID) or string (name)
     * 			Mandatory
     * 	Example Request:
     * 	    GET https://api.cardmarket.com/ws/v2.0/users/9999
     * 	    GET https://api.cardmarket.com/ws/v2.0/users/Maxi
     * 
     * @param responseContent
     * @return
     */
    public static UserWrapper processUsers(String responseContent) {
        Gson gson = new Gson();
        UserWrapper article = gson.fromJson(responseContent, UserWrapper.class);
        return article;
    }
    
    
    
    /**
     * Search for users by part of/whole userName
     * 
     * FIND USERS:
     * 	Allowed HTTP Methods:
     * 		GET - returns users where the username is matching the given search string. The given search string must be at least 3 characters long. A maximum of 50 users is returned.
     * 	Resource Information:
     * 		Authentication - Only required for App
     * 		Response Format - XML or JSON
     * 		Response Object - User
     * 		API Version - 2.0
     * 		Resource URI - https://api.cardmarket.com/ws/v2.0/users/find?
     * 	Parameters:
     * 		search (query parameter):
     * 			search string
     * 			Type: string
     * 			Mandatory
     * 	Example Request:
     * 	    https://api.cardmarket.com/ws/v2.0/users/find?search=Tommi
     * 
     * @param responseContent
     * @return
     */
    public static UsersWrapper processFindUsers(String responseContent) {
        Gson gson = new Gson();
        UsersWrapper article = gson.fromJson(responseContent, UsersWrapper.class);
        return article;
    }
    
    
    
    /**
     * USER ARTICLES:
     * 	Allowed HTTP Methods:
     * 		GET - Returns the available articles for a specified user. The response can be paginated by using the start and maxResults parameters.
     * 	Resource Information:
     * 		Authentication - Only required for App
     * 		Response Format - XML or JSON
     * 		Response Object - Article
     * 		API Version - 2.0
     * 		Resource URI - https://api.cardmarket.com/ws/v2.0/users/:idUser/articles
     * 	Parameters:
     * 		idUser:
     * 			User ID or Username
     * 			Type: integer (ID) or string (name)
     * 			Mandatory
     * 		idGame:
     * 			Game ID (query parameter)
     * 			Type: integer
     * 			Optional (if not provided, 1 for Magic is default)
     * 		start and maxResults (query parameter):
     * 			If specified, only maxResults entities are returned starting from start
     * 			Type: integer and integer
     * 			Optional (if provided, both must be provided)
     * 			See also: Partial Content Documentation
     * 	Example Request:
     * 		https://api.cardmarket.com/ws/v2.0/users/karmacrow/articles?start=0&maxResults=100
     * 
     * @param responseContent
     * @return
     */
    public static ArticleWrapper processUserArticles(String responseContent) {
        Gson gson = new Gson();
        ArticleWrapper article = gson.fromJson(responseContent, ArticleWrapper.class);
        return article;
    }
}

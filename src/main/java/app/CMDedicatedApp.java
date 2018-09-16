package app;

import java.io.IOException;

import app.api.ApiCaller;
import app.security.UserCredentials;
import app.security.UserCredentialsLoader;
import app.service.ExpansionsService;
import entities.ExpansionWrapper;
import parsers.CardmarketParserAPIv2_0;

public class CMDedicatedApp {
    
    private ApiCaller api;
    
    private ExpansionsService expansionsService;
    


    /**
     * Constructor. Fill parameters according to given MKM profile app parameters.
     * 
     * @param appToken
     * @param appSecret
     * @param accessToken
     * @param accessSecret
     */
    public CMDedicatedApp(UserCredentials credentials) {
        
        this.api = new ApiCaller(credentials);
        this.expansionsService = new ExpansionsService();
    }


    /**
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        // USAGE EXAMPLE

        UserCredentials credentials = UserCredentialsLoader.loadCredentials();

        CMDedicatedApp app = new CMDedicatedApp(credentials);
        
        app.updateExpansions();

//        app.setDebug(true);
        

        /*
         * API V2.0 REQUESTS
         * =============================================================================
         * ==================================================================
         */
        /*
        if (app.request("https://sandbox.cardmarket.com/ws/v2.0/output.json/account")) {
            System.out.println(CardmarketParserAPIv2_0.processAccount(app.responseContent()));
        }

        
        if (app.request("https://sandbox.cardmarket.com/ws/v2.0/output.json/games")) {    
            System.out.println(CardmarketParserAPIv2_0.processGames(app.responseContent()));
        }
         */
        

        


        /*
        
        if (app.request("https://sandbox.cardmarket.com/ws/v2.0/output.json/expansions/2110/singles")) {
            System.out.println(CardmarketParserAPIv2_0.processExpansionSingles(app.responseContent()));
        }
        

        if (app.request("https://sandbox.cardmarket.com/ws/v2.0/output.json/products/361996")) {
            System.out.println(CardmarketParserAPIv2_0.processProduct(app.responseContent()));
        }
        
        String cardName = encode("Wandering Ones");
        if (app.request("https://sandbox.cardmarket.com/ws/v2.0/output.json/products/find?search="+cardName)) { // more parameters for partial search
//            if (app.request("https://sandbox.cardmarket.com/ws/v2.0/output.json/products/find?search=12238")) { // 204 No COntent
            System.out.println(CardmarketParserAPIv2_0.processFindProducts(app.responseContent()));
        }
        
        
         */    
//        if (app.request("https://sandbox.cardmarket.com/ws/v2.0/output.json/articles/12238")) {
//            ArticleWrapper wrapper = CardmarketParserAPIv2_0.processFindArticles(app.responseContent());
//            System.out.println(wrapper);
//            ArticlesDAO dao = new ArticlesDAO();
//            dao.insertArticles(wrapper);
//        }
        
//        Article article = new ArticlesService().getArticle(444418);
//        System.out.println(article);
        
        
        /*
        if (app.request("https://sandbox.cardmarket.com/ws/v2.0/output.json/metaproducts/7203")) {
            System.out.println(CardmarketParserAPIv2_0.processMetaproducts(app.responseContent()));
        }
        
        String urlParam = encode("Wandering Ones");
        if (app.request("https://sandbox.cardmarket.com/ws/v2.0/output.json/metaproducts/find?search="+urlParam)) {
            System.out.println(CardmarketParserAPIv2_0.processFindMetaproducts(app.responseContent()));
        }
        
        
        OffsetDateTime offsDT = OffsetDateTime.parse("2009-10-20T21:29:18+0200", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssxx"));
//        String user = "PlayerSpot";
//        String user = "51816";
        String user = "b30dd6b036e87746495b04be1de97149";
        if (app.request("https://sandbox.cardmarket.com/ws/v2.0/output.json/users/"+user)) {
            System.out.println(CardmarketParserAPIv2_0.processUsers(app.responseContent()));
        }
        
        
        String user = "Player";
        String user = "51816";
        if (app.request("https://sandbox.cardmarket.com/ws/v2.0/output.json/users/find?search="+user)) {
            System.out.println(CardmarketParserAPIv2_0.processFindUsers(app.responseContent()));
        }
        
        if (app.request("https://sandbox.cardmarket.com/ws/v2.0/output.json/users/b30dd6b036e87746495b04be1de97149/articles?idGame=1&start=0&maxResults=2")) {
            if (app.request("https://sandbox.cardmarket.com/ws/v2.0/output.json/users/b30dd6b036e87746495b04be1de97149/articles?idGame=1&maxResults=2")) {
            System.out.println(CardmarketParserAPIv2_0.processUserArticles(app.responseContent()));
        }
        
         */
        
    }

    private void updateExpansions() {
        
        if (api.request("https://sandbox.cardmarket.com/ws/v2.0/output.json/games/1/expansions")) {
            ExpansionWrapper expansionsWrapper = CardmarketParserAPIv2_0.processExpansions(api.responseContent());
            expansionsService.insertExpansionsIfNotPresent(expansionsWrapper);
        }
    }



    
    
    
 // V1.0 API ===========================================================================================================================        
//  if (app.request("https://sandbox.cardmarket.com/ws/v1.1/output.json/games")) {    // all games, mtg id:1
////     processExpansions(app.responseContent());
// }        

// if (app.request("https://sandbox.cardmarket.com/ws/v1.1/output.json/account")) {    // account details
//     // .. process(app.responseContent());
// }

 /*
  * /expansion/:idGame
  */
// if (app.request("https://sandbox.cardmarket.com/ws/v1.1/output.json/expansion/1")) {  // all expansions for game with id 3
//    processExpansions(app.responseContent());                                          // processing expansions
// }

 /*
  * /expansion/:idGame/:name
  */
// if (app.request("https://sandbox.cardmarket.com/ws/v1.1/output.json/expansion/1/Dominaria")) {    // all products for expansion
//     // .. process products
// }

 /*
  * /metaproducts/:name/:idGame/:idLanguage
  */
// String cardname = encode("Æthersnatch");
// if (app.request(String.format("https://sandbox.cardmarket.com/ws/v1.1/output.json/metaproducts/%s/1/1",cardname))) { // gimme all metaproducts by name Island - gives product ID for islands
//     // .. process(app.responseContent());
// }

 /*
  * /product/:idProduct
  */
// if (app.request("https://sandbox.cardmarket.com/ws/v1.1/output.json/product/285840")) { // specific product type from all expansions
//     // .. process(app.responseContent());
// }

 /*
  * /metaproduct/:idMetaproduct
  */
// if (app.request("https://sandbox.cardmarket.com/ws/v1.1/output.json/metaproduct/221090")) { // specific product type of island
//     // .. process(app.responseContent());
// }

 /*
  * /articles/:idProduct[/:start]
  */
// if (app.request("https://sandbox.cardmarket.com/ws/v1.1/output.json/articles/285840")) {    // works
//     // .. process(app.responseContent());
// }

 /*
  * /products/:name/:idGame/:idLanguage/:isExact[/:start]
  */
// String cardname = encode("Æthersnatch");
// if (app.request(String.format("https://sandbox.cardmarket.com/ws/v1.1/output.json/products/%s/1/1/true",cardname))) { // gimme all metaproducts by name Island - gives product ID for islands
//     // .. process(app.responseContent());
// }
}

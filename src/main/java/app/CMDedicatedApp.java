package app;

import java.io.IOException;

import app.service.CardmarketService;

public class CMDedicatedApp {
    
    public CardmarketService cmService = new CardmarketService();

    /**
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        // USAGE EXAMPLE
        CMDedicatedApp cmApp = new CMDedicatedApp();
        
//        cmApp.cmService.updateExpansions();
//        cmApp.cmService.updateExpansionSingles(38);
        cmApp.cmService.updateAllExpansionSingles();
        

        

//        app.findProducts("Springleaf");                                           // fine
//        app.findProducts("Springleaf Drum");                                           // fine
//        app.findProducts("Springleaf Drum&exact=true&idGame=1&idLanguage=1");                                           // fine
//        app.findProducts("Griselbrand");                                           // fine
//        app.findProducts("John Avon Art: Farway Island Playmat");                  // 400 bad request
//        app.findProducts("John%20Avon%20Art%3A%20Farway%20Island%20Playmat");    // 204 without url encode
//        app.findProducts("Raven Playmat");                                       // 400 bad request - request structure is wrong
//        app.findProducts("Raven%20Playmat");                                       // 204 no content
        /*
         * API V2.0 REQUESTS
         * =============================================================================
         * ==================================================================
         */
        /*
        if (app.request("https://sandbox.cardmarket.com/ws/v2.0/output.json/account")) {
            System.out.println(ApiParserV2_0.processAccount(app.responseContent()));
        }

        
        if (app.request("https://sandbox.cardmarket.com/ws/v2.0/output.json/games")) {    
            System.out.println(ApiParserV2_0.processGames(app.responseContent()));
        }
         */

        /*

        if (app.request("https://sandbox.cardmarket.com/ws/v2.0/output.json/products/361996")) {
            System.out.println(ApiParserV2_0.processProduct(app.responseContent()));
        }
        
        String cardName = encode("Wandering Ones");
        if (app.request("https://sandbox.cardmarket.com/ws/v2.0/output.json/products/find?search="+cardName)) { // more parameters for partial search
//            if (app.request("https://sandbox.cardmarket.com/ws/v2.0/output.json/products/find?search=12238")) { // 204 No COntent
            System.out.println(ApiParserV2_0.processFindProducts(app.responseContent()));
        }
        
        
         */    
//        if (app.request("https://sandbox.cardmarket.com/ws/v2.0/output.json/articles/12238")) {
//            ArticleWrapper wrapper = ApiParserV2_0.processFindArticles(app.responseContent());
//            System.out.println(wrapper);
//            ArticlesDAO dao = new ArticlesDAO();
//            dao.insertArticles(wrapper);
//        }
        
//        Article article = new ArticlesService().getArticle(444418);
//        System.out.println(article);
        
        
        /*
        if (app.request("https://sandbox.cardmarket.com/ws/v2.0/output.json/metaproducts/7203")) {
            System.out.println(ApiParserV2_0.processMetaproducts(app.responseContent()));
        }
        

        
        
        OffsetDateTime offsDT = OffsetDateTime.parse("2009-10-20T21:29:18+0200", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssxx"));
//        String user = "PlayerSpot";
//        String user = "51816";
        String user = "b30dd6b036e87746495b04be1de97149";
        if (app.request("https://sandbox.cardmarket.com/ws/v2.0/output.json/users/"+user)) {
            System.out.println(ApiParserV2_0.processUsers(app.responseContent()));
        }
        
        
        String user = "Player";
        String user = "51816";
        if (app.request("https://sandbox.cardmarket.com/ws/v2.0/output.json/users/find?search="+user)) {
            System.out.println(ApiParserV2_0.processFindUsers(app.responseContent()));
        }
        
        if (app.request("https://sandbox.cardmarket.com/ws/v2.0/output.json/users/b30dd6b036e87746495b04be1de97149/articles?idGame=1&start=0&maxResults=2")) {
            if (app.request("https://sandbox.cardmarket.com/ws/v2.0/output.json/users/b30dd6b036e87746495b04be1de97149/articles?idGame=1&maxResults=2")) {
            System.out.println(ApiParserV2_0.processUserArticles(app.responseContent()));
        }
        
         */
        
    }


    

    /**
     * pretty much useless as authorisation doesn't work on this call.
     * The other way is to get all singles, store in db and query db instead.
     * @param productName
     */
//    private void findProducts(String productName) {
//        //check database if product is there
//            //if not, search for product in shop, insert into db
////        productName = ApiCaller.encode(productName);
//        if (api.request("https://sandbox.cardmarket.com/ws/v2.0/output.json/products/find?search="+productName)) {
//            System.out.println(ApiParserV2_0.processFindProducts(api.responseContent()));
//        }
//    }
    


    
    
    
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

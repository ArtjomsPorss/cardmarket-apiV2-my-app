package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.StringJoiner;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.net.UrlEscapers;

import app.database.CardMarketDAO;
import app.security.UserCredentials;
import app.security.UserCredentialsLoader;
import entities.ArticleWrapper;
import parsers.CardmarketParserAPIv2_0;

public class M11DedicatedApp {
    
    private static final Logger logger = LogManager.getLogger(M11DedicatedApp.class);

    private String _mkmAppToken;
    private String _mkmAppSecret;
    private String _mkmAccessToken;
    private String _mkmAccessTokenSecret;

    private Throwable _lastError;
    private int _lastCode;
    private String _lastContent;
    private boolean _debug;

    /**
     * Constructor. Fill parameters according to given MKM profile app parameters.
     * 
     * @param appToken
     * @param appSecret
     * @param accessToken
     * @param accessSecret
     */
    public M11DedicatedApp(UserCredentials credentials) {
        _mkmAppToken = credentials.getMkmAppToken();
        _mkmAppSecret = credentials.getMkmAppSecret();
        _mkmAccessToken = credentials.getMkmAccessToken();
        _mkmAccessTokenSecret = credentials.getMkmAccessTokenSecret();

        _lastError = null;
        _debug = false;
    }

    /**
     * Activates the console debug messages
     * 
     * @param flag true if you want to enable console messages; false to disable any
     *             notification.
     */
    public void setDebug(boolean flag) {
        _debug = flag;
    }

    /**
     * Encoding function. To avoid deprecated version, the encoding used is UTF-8.
     * 
     * @param str
     * @return
     * @throws UnsupportedEncodingException
     */
    private String rawurlencode(String str) throws UnsupportedEncodingException {
        return URLEncoder.encode(str, "UTF-8");
    }

    public static String encode(String str) {
        return UrlEscapers.urlFragmentEscaper().escape(str);
    }

    private void _debug(String msg) {
        if (_debug) {
            System.out.print(GregorianCalendar.getInstance().getTime());
            System.out.print(" > ");
            System.out.println(msg);
        }
    }

    /**
     * Get last Error exception.
     * 
     * @return null if no errors; instead the raised exception.
     */
    public Throwable lastError() {
        return _lastError;
    }

    /**
     * Perform the request to given url with OAuth 1.0a API.
     * 
     * @param requestURL url to be requested. Ex.
     *                   https://api.cardmarket.com/ws/v1.1/products/island/1/1/false
     * @return true if request was successfully executed. You can retrieve the
     *         content with responseContent();
     */
    public boolean request(String requestURL) {
        _lastError = null;
        _lastCode = 0;
        _lastContent = "";
        try {
            
            _debug("Requesting "+requestURL);
            
            String realm = requestURL.indexOf("?") == -1 ? requestURL : requestURL.substring(0, requestURL.indexOf("?"));
            String urlParameters = requestURL.indexOf("?") == -1 ? "" : requestURL.substring(requestURL.indexOf("?")+1);
            String oauth_version = "1.0" ;
            String oauth_consumer_key = _mkmAppToken ;
            String oauth_token = _mkmAccessToken ;
            String oauth_signature_method = "HMAC-SHA1" ;
            String oauth_timestamp = "" + (System.currentTimeMillis()/1000) ;
            String oauth_nonce = "" + System.currentTimeMillis() ;
            
//            String encodedRequestURL = rawurlencode(requestURL) ;
           
            String baseString = "GET&" + rawurlencode(realm) + "&" ;
            
            // creating string with parameters
            // setting oauth parameters
            List<String> parameters = new ArrayList<String>(Arrays.asList(  //Arrays.asList creates unmodifiable list, wrapping with ArrayList to prevent Exception 
                    "oauth_consumer_key=" + rawurlencode(oauth_consumer_key),
                    "oauth_nonce=" + rawurlencode(oauth_nonce),
                    "oauth_signature_method=" + rawurlencode(oauth_signature_method),
                    "oauth_timestamp=" + rawurlencode(oauth_timestamp),
                    "oauth_token=" + rawurlencode(oauth_token),
                    "oauth_version=" + rawurlencode(oauth_version)));
            // adding URL parameters if present
            if(!urlParameters.trim().isEmpty()) {
                String[] queryParameterArray = urlParameters.split("&");
                parameters.addAll(Arrays.asList(queryParameterArray));
            }
            // must be alphabetically sorted
            Collections.sort(parameters);
            // joining parameters back with & signs
            StringJoiner parametersJoined = new StringJoiner("&");
            parameters.stream().forEach(parametersJoined::add);
                                
            baseString = baseString + rawurlencode(parametersJoined.toString()) ;
            
            String signingKey = rawurlencode(_mkmAppSecret) + 
                        "&" +
                        rawurlencode(_mkmAccessTokenSecret) ;
            
            Mac mac = Mac.getInstance("HmacSHA1");
            SecretKeySpec secret = new SecretKeySpec(signingKey.getBytes(), mac.getAlgorithm());
            mac.init(secret);
            byte[] digest = mac.doFinal(baseString.getBytes());
            String oauth_signature = DatatypeConverter.printBase64Binary(digest);    //Base64.encode(digest) ; 
            
            String authorizationProperty = 
                    "OAuth " +
                    "realm=\"" + realm + "\", " + 
                    "oauth_version=\"" + oauth_version + "\", " +
                    "oauth_timestamp=\"" + oauth_timestamp + "\", " +
                    "oauth_nonce=\"" + oauth_nonce + "\", " +
                    "oauth_consumer_key=\"" + oauth_consumer_key + "\", " +
                    "oauth_token=\"" + oauth_token + "\", " +
                    "oauth_signature_method=\"" + oauth_signature_method + "\", " +
                    "oauth_signature=\"" + oauth_signature + "\"" ;
            
            HttpURLConnection connection = (HttpURLConnection) new URL(requestURL).openConnection();
            connection.addRequestProperty("Authorization", authorizationProperty) ;
            connection.connect() ;
            
            // from here standard actions... 
            // read response code... read input stream.... close connection...
            
            _lastCode = connection.getResponseCode();
            
            _debug("Response Code is "+_lastCode);
            
            if (206 == _lastCode || 200 == _lastCode || 401 == _lastCode || 404 == _lastCode || 403 == _lastCode) {
                BufferedReader rd = new BufferedReader(new InputStreamReader(
                        _lastCode == 200 ? connection.getInputStream() : connection.getErrorStream()));
                StringBuffer sb = new StringBuffer();
                String line;
                while ((line = rd.readLine()) != null) {
                    sb.append(line);
                }
                rd.close();
                _lastContent = sb.toString();
                _debug("Response Content is \n" + _lastContent);
            } else if (204 == _lastCode) {
                _debug("No Content");
            } else if (206 == _lastCode) {
                _debug("Partial Content");
            }
            
            return (_lastCode == 200);
            
        } catch (Exception e) {
            e.printStackTrace();
            _debug("(!) Error while requesting "+requestURL);
            _lastError = e;
        }
        return false;
    }

    /**
     * Get response code from last request.
     * 
     * @return
     */
    public int responseCode() {
        return _lastCode;
    }

    /**
     * Get response content from last request.
     * 
     * @return
     */
    public String responseContent() {
        return _lastContent;
    }

    /**
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        // USAGE EXAMPLE

        UserCredentials credentials = UserCredentialsLoader.loadCredentials();

        M11DedicatedApp app = new M11DedicatedApp(credentials);

        app.setDebug(true);

//        if (app.request("https://sandbox.cardmarket.com/ws/v1.1/output.json/games")) {    // all games, mtg id:1
////            processExpansions(app.responseContent());
//        }        

//        if (app.request("https://sandbox.cardmarket.com/ws/v1.1/output.json/account")) {    // account details
//            // .. process(app.responseContent());
//        }

        /*
         * /expansion/:idGame
         */
//        if (app.request("https://sandbox.cardmarket.com/ws/v1.1/output.json/expansion/1")) {	// all expansions for game with id 3
//        	 processExpansions(app.responseContent());                                          // processing expansions
//        }

        /*
         * /expansion/:idGame/:name
         */
//        if (app.request("https://sandbox.cardmarket.com/ws/v1.1/output.json/expansion/1/Dominaria")) {	// all products for expansion
//            // .. process products
//        }

        /*
         * /metaproducts/:name/:idGame/:idLanguage
         */
//        String cardname = encode("Æthersnatch");
//        if (app.request(String.format("https://sandbox.cardmarket.com/ws/v1.1/output.json/metaproducts/%s/1/1",cardname))) { // gimme all metaproducts by name Island - gives product ID for islands
//            // .. process(app.responseContent());
//        }

        /*
         * /product/:idProduct
         */
//        if (app.request("https://sandbox.cardmarket.com/ws/v1.1/output.json/product/285840")) { // specific product type from all expansions
//            // .. process(app.responseContent());
//        }

        /*
         * /metaproduct/:idMetaproduct
         */
//        if (app.request("https://sandbox.cardmarket.com/ws/v1.1/output.json/metaproduct/221090")) { // specific product type of island
//            // .. process(app.responseContent());
//        }

        /*
         * /articles/:idProduct[/:start]
         */
//        if (app.request("https://sandbox.cardmarket.com/ws/v1.1/output.json/articles/285840")) {    // works
//            // .. process(app.responseContent());
//        }

        /*
         * /products/:name/:idGame/:idLanguage/:isExact[/:start]
         */
//        String cardname = encode("Æthersnatch");
//        if (app.request(String.format("https://sandbox.cardmarket.com/ws/v1.1/output.json/products/%s/1/1/true",cardname))) { // gimme all metaproducts by name Island - gives product ID for islands
//            // .. process(app.responseContent());
//        }

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

        if (app.request("https://sandbox.cardmarket.com/ws/v2.0/output.json/games/1/expansions")) {
            System.out.println(CardmarketParserAPIv2_0.processExpansions(app.responseContent()));
        }


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
        if (app.request("https://sandbox.cardmarket.com/ws/v2.0/output.json/articles/12238")) {
            ArticleWrapper wrapper = CardmarketParserAPIv2_0.processFindArticles(app.responseContent());
            System.out.println(wrapper);
            CardMarketDAO dao = new CardMarketDAO();
            dao.createArticles(wrapper);
        }
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



}




/*
    public boolean request(String requestURL) {
        _lastError = null;
        _lastCode = 0;
        _lastContent = "";
        try {

            _debug("Requesting " + requestURL);

            String urlParameters = requestURL.substring(requestURL.indexOf("?"), requestURL.length());
            String realm = requestURL.substring(0, requestURL.indexOf("?"));
            String oauth_version = "1.0";
            String oauth_consumer_key = _mkmAppToken;
            String oauth_token = _mkmAccessToken;
            String oauth_signature_method = "HMAC-SHA1";
            String oauth_timestamp = "" + (System.currentTimeMillis() / 1000);
//            String oauth_timestamp = "1407917892" ;
            String oauth_nonce = "" + System.currentTimeMillis();
//            String oauth_nonce = "53eb1f44909d6" ;

            
            String encodedRequestURL = rawurlencode(realm);
            String baseString = "GET&" + encodedRequestURL + "&";

            // creating string with parameters
            // setting oauth parameters
            List<String> parameters = new ArrayList<String>(Arrays.asList(  //Arrays.asList creates unmodifiable list, wrapping with ArrayList to prevent Exception 
                    "oauth_consumer_key=" + rawurlencode(oauth_consumer_key),
                    "oauth_nonce=" + rawurlencode(oauth_nonce),
                    "oauth_signature_method=" + rawurlencode(oauth_signature_method),
                    "oauth_timestamp=" + rawurlencode(oauth_timestamp), "oauth_token=" + rawurlencode(oauth_token),
                    "oauth_version=" + rawurlencode(oauth_version)));
            // adding URL parameters
            String[] queryParameterArray = urlParameters.split("&");
            parameters.addAll(Arrays.asList(queryParameterArray));
            // must be alphabetically sorted
            Collections.sort(parameters);
            // joining parameters back with & signs
            StringJoiner parametersJoined = new StringJoiner("&");
            parameters.stream().forEach(parametersJoined::add);
            


            String signingKey = rawurlencode(_mkmAppSecret) + "&" + rawurlencode(_mkmAccessTokenSecret);

            Mac mac = Mac.getInstance("HmacSHA1");
            SecretKeySpec secret = new SecretKeySpec(signingKey.getBytes(), mac.getAlgorithm());
            mac.init(secret);
            baseString = baseString + rawurlencode(parametersJoined.toString());
            byte[] digest = mac.doFinal(baseString.getBytes());
            String oauth_signature = DatatypeConverter.printBase64Binary(digest); // Base64.encode(digest) ;

            String authorizationProperty = 
                    "OAuth " +
                    "realm=\"" + realm + "\", " + 
                    "oauth_version=\"" + oauth_version + "\", " +
                    "oauth_timestamp=\"" + oauth_timestamp + "\", " +
                    "oauth_nonce=\"" + oauth_nonce + "\", " +
                    "oauth_consumer_key=\"" + oauth_consumer_key + "\", " +
                    "oauth_token=\"" + oauth_token + "\", " +
                    "oauth_signature_method=\"" + oauth_signature_method + "\", " +
                    "oauth_signature=\"" + oauth_signature + "\"" ;

            HttpURLConnection connection = (HttpURLConnection) new URL(realm+urlParameters).openConnection();
            connection.addRequestProperty("Authorization", authorizationProperty);
            connection.connect();

            // from here standard actions...
            // read response code... read input stream.... close connection...

            _lastCode = connection.getResponseCode();

            _debug("Response Code is " + _lastCode);

            if (200 == _lastCode || 401 == _lastCode || 404 == _lastCode || 403 == _lastCode) {
                BufferedReader rd = new BufferedReader(new InputStreamReader(
                        _lastCode == 200 ? connection.getInputStream() : connection.getErrorStream()));
                StringBuffer sb = new StringBuffer();
                String line;
                while ((line = rd.readLine()) != null) {
                    sb.append(line);
                }
                rd.close();
                _lastContent = sb.toString();
                _debug("Response Content is \n" + _lastContent);
            }

            return (_lastCode == 200);

        } catch (Exception e) {
            _debug("(!) Error while requesting " + requestURL);
            _lastError = e;
        }
        return false;
    }
 */
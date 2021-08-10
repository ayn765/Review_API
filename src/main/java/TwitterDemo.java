import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;

public class TwitterDemo {

    public static void main(String[] args) {

        String APIKey = "4mV9AmsFeba0DkIvVfb5sIzyJ";
        String APISecretKey = "iyOp0fQ5oMqrM8smoh4aF5tF9qsxpKPMPl9V5LKPO838XWkBjG";
        String BearerToken = "AAAAAAAAAAAAAAAAAAAAAPzGOwEAAAAAH8ZlhzURjfJ90fwj1VEevOKHyw8%3Dyy0JpKlwLTJl7Gk2mzCltQcBYZGoANhT9kxtXsajaKeirLeLYI";
        String AccessToken = "1384991907030441986-lbK4jZp5tg3W0OGZ64SRHL6ZXwZlne";
        String AccessTokenSecret = "YS2pbDnJreLqM0ZWUSCJoa16XHJ3peYBMTlQn6kYV8pRq";
//        RestAssured.baseURI = "https://api.twitter.com/1.1";
        given().auth().oauth(APIKey, APISecretKey, AccessToken, AccessTokenSecret).header("Content-Type", "application/json")
                .queryParam("id", 946738)
                .when().get("https://api.twitter.com/1.1/trends/place.json")
//                .param("status", "Real queens fix each other's crowns.")
//                .when().post("/statuses/update.json")
                .then().extract().response().asString();

    }
}
//      return given().auth().oauth(this.APIKey, this.APISecretKey, this.Token, this.TokenSecret)
//                    .param("status", twitterPost)
//                    .when().post(this.BASE_URL + this.CREATE_TWEET_ENDPOINT)
//                    .then();
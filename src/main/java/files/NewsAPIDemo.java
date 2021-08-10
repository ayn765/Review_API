package files;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class NewsAPIDemo {

    String key = "68d25aaee81b4d12808e66817ed4228c";

    @Test
    public void newsTest(){
        RestAssured.baseURI = "https://newsapi.org";
        String url = baseURI + "/v2/everything"+"?q=keyword&apiKey="+key;
        String response = given().when().get(url).then().contentType(ContentType.JSON).extract().body().asString();
        System.out.println(response);

    }


}

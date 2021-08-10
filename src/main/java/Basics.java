import files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Basics extends CommonMethods{
    public static void main(String[] args) {

        //validate if Add Place API is working as expected

        //given - all input details
        //when - submit API - resource and http method
        //then - validate the response

        //add place -> update place with new address -> get place to validate if new place is present in response

        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String key = "qaclick123";
        String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body(Payload.addPlace())
                .when().post("/maps/api/place/add/json")
                .then().assertThat().statusCode(200).body("scope", equalTo("APP"))
                .header("server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();


        System.out.println("Response is " + response);
        JsonPath js = new JsonPath(response); // takes a String and converts into JSON, then parses JSON
        String placeID = js.getString("place_id");
        System.out.println("Place id is " + placeID);

        //update place
        given().log().all().queryParam("key", "qacklick123").header("Content-Type", "application/json")
                .body("{\n" +
                        "\"place_id\":\"" + placeID + "\",\n" +
                        "\"address\":\"70 winter walk, USA\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}")
                .when().put("/maps/api/place/update/json")
                .then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));

        //get updated address
        String expectedAddress = "70 winter walk, USA";
        String response2 = given().queryParam("key", key).queryParam("place_id", placeID)
                .when().get("/maps/api/place/get/json")
                .then().assertThat().body("address", equalTo(expectedAddress))
                .extract().body().asString();
        JsonPath js1 = rawToJSON(response2);
        String actualAddress = js1.getString("address");
        System.out.println("Actual address is " + actualAddress);

        Assert.assertEquals(actualAddress, expectedAddress, "Address is not correct");







    }
}

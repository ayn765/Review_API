import files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DynamicJson extends CommonMethods{

    @Test (dataProvider = "BookInfo")
    public void addBook(String isbn, String aisle){
        RestAssured.baseURI = "http://216.10.245.166";
        String response = given().log().all().header("Content-Type", "application/json")
                .body(Payload.addBook(isbn, aisle))
                .when().post("Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();

        JsonPath js = rawToJSON(response);
        String id = js.get("ID");
        System.out.println("Book ID is " + id);

        //deleteBook

    }
    @DataProvider(name = "BookInfo")
    public Object[][] getData(){

        return new Object[][]{{"asdf","5443"}, {"loihg", "9348"}, {"mcge", "8723"}};
    }
}
